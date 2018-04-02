package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.UserDatabase;
import java2.models.User;
import org.junit.Before;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BanUserServiceTest {
    @Mock private UserDatabase userDatabase;
    @Mock private BanUserValidator validator;

    @InjectMocks
    private BanUserService service = new BanUserService();

    @Test
    public void shouldReturnSuccess() {
        List<ValidationError> errors = new ArrayList<>();
        User user = Mockito.mock(User.class);
        Mockito.when(validator.validate("login")).
                thenReturn(errors);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        ServiceResponse response = service.banUser("login");
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(userDatabase).banUser(any(User.class));
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(null)).
                thenReturn(errors);
        ServiceResponse response = service.banUser(null);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}