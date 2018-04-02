package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
import java2.models.User;
import java2.models.UserState;
import org.junit.Before;
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
public class BanUserValidatorTest {
    @Mock private UserDatabase userDatabase;

    @InjectMocks
    private BanUserValidator validator = new BanUserValidator();

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        List<ValidationError> errors = validator.validate(null);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }

    @Test
    public void shouldReturnErrorWhenUserAlreadyBanned() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(Optional.of(user).get().getState())
                .thenReturn("BANNED");
        List<ValidationError> errors = validator.validate("login");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User already banned!");
    }

    @Test
    public void shouldReturnSuccess() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(Optional.of(user).get().getState())
                .thenReturn("ACTIVE");
        List<ValidationError> errors = validator.validate("login");
        assertEquals(errors.size(), 0);
    }
}