package java2.businesslogic.logout;

import java2.businesslogic.ValidationError;
import java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServiceImplTest {
    @Mock private LogoutValidator validator;

    @InjectMocks
    private LogoutService service = new LogoutServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        LogoutRequest request = new LogoutRequest("login", 'U');
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        LogoutResponse response = service.logout(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void shouldReturnFail() {
        LogoutRequest request = new LogoutRequest("login", 'U');
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        LogoutResponse response = service.logout(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}