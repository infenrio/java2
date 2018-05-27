package java2.businesslogic.login;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import java2.domain.UserState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginValidatorImplTest {
    @Mock private UserRepository userRepository;

    @InjectMocks
    private LoginValidator validator = new LoginValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByLoginAndPassword("login", "password")).
                thenReturn(Optional.of(user));
        LoginRequest request = new LoginRequest("login", "password", 'U');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        LoginRequest request = new LoginRequest(null, "password", 'U');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        LoginRequest request = new LoginRequest("login", null, 'U');
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenRoleIsInvalid() {
        LoginRequest request = new LoginRequest("login", "password", 'B');
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndPassword("login", "password")).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "role");
        assertEquals(errors.get(0).getErrorMessage(), "Has not valid value!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsIncorrect() {
        LoginRequest request = new LoginRequest("login", "password", 'U');
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByLoginAndPassword("login", "password")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect login data!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        LoginRequest request = new LoginRequest("login", "password", 'U');
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.empty());
        Mockito.when(userRepository.findByLoginAndPassword("login", "password")).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsBanned() {
        LoginRequest request = new LoginRequest("login", "password", 'U');
        User user = Mockito.mock(User.class);
        UserState userState = Mockito.mock(UserState.class);
//        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
//                thenReturn(Optional.of(user));
//        Mockito.when(userRepository.findByLoginAndPassword("login", "password")).
//                thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(user.getState()).thenReturn(userState);
        Mockito.when(userState.getId()).thenReturn("BANNED");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User is banned!");
    }
}