package java2.businesslogic.login;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
    @Mock private LoginValidator validator;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private LoginService service = new LoginServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        LoginRequest request = new LoginRequest("login", "password", 'U');
        User user = Mockito.mock(User.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.of(user));
        LoginResponse response = service.login(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void shouldReturnFail() {
        LoginRequest request = new LoginRequest("login", "password", 'U');
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        LoginResponse response = service.login(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}