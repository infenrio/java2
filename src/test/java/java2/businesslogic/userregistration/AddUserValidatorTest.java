package java2.businesslogic.userregistration;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
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
public class AddUserValidatorTest {
    @Mock private UserDatabase userDatabase;

    @InjectMocks
    private AddUserValidator validator = new AddUserValidator();

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        List<ValidationError> errors = validator.validate(null, "password", "name", "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        List<ValidationError> errors = validator.validate("login", null, "name", "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        List<ValidationError> errors = validator.validate("login", "password", null, "email");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "name");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsEmpty() {
        List<ValidationError> errors = validator.validate("login", "password", "name", null);
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
        List<ValidationError> errors = validator.validate("login", "password", "name", "email");
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
        List<ValidationError> errors = validator.validate("login", "password", "name", "email");
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
        List<ValidationError> errors = validator.validate("login", "password", "name", "email");
        assertEquals(errors.size(), 0);
    }

}