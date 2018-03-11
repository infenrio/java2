package java2.businesslogic.adduser;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
import java2.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddUserValidatorTest {
    private UserDatabase userDatabase;
    private AddUserValidator validator;

    @Before
    public void init() {
        userDatabase = Mockito.mock(UserDatabase.class);
        validator = new AddUserValidator(userDatabase);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        List<ValidationError> errors = validator.validate(null, "name", "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        List<ValidationError> errors = validator.validate("login", null, "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsEmpty() {
        List<ValidationError> errors = validator.validate("login", "name", null);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenLoginIsRepeated() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(userDatabase.findByEmail("email")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login", "name", "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsRepeated() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        Mockito.when(userDatabase.findByEmail("email")).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate("login", "name", "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated!");
    }

    @Test
    public void shouldReturnSuccess() {
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        Mockito.when(userDatabase.findByEmail("email")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login", "name", "email");
        assertEquals(errors.size(), 0);
    }

}