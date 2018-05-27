package java2.businesslogic.userban;

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
public class UserBanValidatorImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserBanValidator validator = new UserBanValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        User user = Mockito.mock(User.class);
        UserState userState = Mockito.mock(UserState.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(user.getState()).thenReturn(userState);
        Mockito.when(userState.getId()).thenReturn("ACTIVE");
        UserBanRequest request = new UserBanRequest("login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        UserBanRequest request = new UserBanRequest(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.empty());
        UserBanRequest request = new UserBanRequest("login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }

    @Test
    public void shouldReturnErrorWhenUserAlreadyBanned() {
        User user = Mockito.mock(User.class);
        UserState userState = Mockito.mock(UserState.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(user.getState()).thenReturn(userState);
        Mockito.when(userState.getId()).thenReturn("BANNED");
        UserBanRequest request = new UserBanRequest("login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User already banned!");
    }
}