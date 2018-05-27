package java2.businesslogic.registration;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
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
public class RegistrationValidatorImplTest {
    @Mock private UserRepository userRepository;

    @InjectMocks
    private RegistrationValidator validator = new RegistrationValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail("email")).
                thenReturn(Optional.empty());
        RegistrationRequest request = new RegistrationRequest("login", "password", "name", "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        RegistrationRequest request = new RegistrationRequest(null, "password", "name", "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("login", null, "name", "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("login", "password", null, "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsEmpty() {
        RegistrationRequest request = new RegistrationRequest("login", "password", "name", null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenLoginIsRepeated() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("email")).
                thenReturn(Optional.empty());
        RegistrationRequest request = new RegistrationRequest("login", "password", "name", "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsRepeated() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail("email")).
                thenReturn(Optional.of(user));
        RegistrationRequest request = new RegistrationRequest("login", "password", "name", "email");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated!");
    }
}