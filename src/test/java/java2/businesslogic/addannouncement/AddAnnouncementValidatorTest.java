package java2.businesslogic.addannouncement;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.models.Announcement;
import java2.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddAnnouncementValidatorTest {
    private AnnouncementDatabase announcementDatabase;
    private UserDatabase userDatabase;

    private AddAnnouncementValidator validator;

    @Before
    public void init() {
        announcementDatabase = Mockito.mock(AnnouncementDatabase.class);
        userDatabase = Mockito.mock(UserDatabase.class);
        validator = new AddAnnouncementValidator(announcementDatabase, userDatabase);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate(null, "title", "description");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate("login", null, "description");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        List<ValidationError> errors = validator.validate("login", "title", null);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsRepeated() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        List<ValidationError> errors = validator.validate("login", "title", "description");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.empty());
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login", "title", "description");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }

    @Test
    public void shouldReturnSuccess() {
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login", "title", "description");
        assertEquals(errors.size(), 0);
    }
}