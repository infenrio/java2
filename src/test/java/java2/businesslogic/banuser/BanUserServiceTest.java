package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.UserDatabase;
import java2.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BanUserServiceTest {
    private UserDatabase userDatabase;
    private BanUserValidator validator;
    private BanUserService service;

    @Before
    public void init() {
        userDatabase = Mockito.mock(UserDatabase.class);
        validator = Mockito.mock(BanUserValidator.class);
        service = new BanUserService(userDatabase, validator);
    }

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
        Mockito.verify(user).ban();
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(null)).
                thenReturn(errors);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        ServiceResponse response = service.banUser(null);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}