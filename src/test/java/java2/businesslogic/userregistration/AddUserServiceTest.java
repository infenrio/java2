package java2.businesslogic.userregistration;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.UserDatabase;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {
    @Mock private UserDatabase userDatabase;
    @Mock private AddUserValidator validator;

    @InjectMocks
    private AddUserService service = new AddUserService();

    @Test
    public void shouldReturnSuccess() {
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate("login", "password", "name", "email")).
                thenReturn(errors);
        ServiceResponse response = service.addUser("login", "password", "name", "email");
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(userDatabase).add(any(User.class));
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(null, "password", "name", "email")).
                thenReturn(errors);
        ServiceResponse response = service.addUser(null, "password", "name", "email");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(userDatabase);
    }
}