package java2.businesslogic.adduser;

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
import static org.mockito.ArgumentMatchers.any;

public class AddUserServiceTest {
    private UserDatabase userDatabase;
    private AddUserValidator validator;
    private AddUserService service;

    @Before
    public void init() {
        userDatabase = Mockito.mock(UserDatabase.class);
        validator = Mockito.mock(AddUserValidator.class);
        service = new AddUserService(userDatabase, validator);
    }

    @Test
    public void shouldReturnSuccess() {
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate("login", "name", "email")).
                thenReturn(errors);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        ServiceResponse response = service.addUser("login", "name", "email");
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(userDatabase).add(any(User.class));
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(null, "name", "email")).
                thenReturn(errors);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        ServiceResponse response = service.addUser(null, "name", "email");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(userDatabase);
    }
}