package java2.businesslogic.logout;

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
public class LogoutValidatorImplTest {
    @Mock private UserRepository userRepository;

    @InjectMocks
    private LogoutValidator validator = new LogoutValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        LogoutRequest request = new LogoutRequest("login", 'U');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        LogoutRequest request = new LogoutRequest(null, 'U');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenRoleIsInvalid() {
        LogoutRequest request = new LogoutRequest("login", 'B');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "role");
        assertEquals(errors.get(0).getErrorMessage(), "Has not valid value!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.empty());
        LogoutRequest request = new LogoutRequest("login", 'U');
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }
}