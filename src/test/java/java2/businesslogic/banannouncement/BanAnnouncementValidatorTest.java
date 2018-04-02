package java2.businesslogic.banannouncement;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.models.Announcement;
import java2.models.AnnouncementState;
import java2.models.User;
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
public class BanAnnouncementValidatorTest {
    @Mock private AnnouncementDatabase announcementDatabase;

    @InjectMocks
    private BanAnnouncementValidator validator = new BanAnnouncementValidator();

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        Mockito.when(Optional.of(announcement).get().getState())
                .thenReturn("ACTIVE");
        List<ValidationError> errors = validator.validate(null, "title");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        List<ValidationError> errors = validator.validate("login", null);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementIsNotPresent() {
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate("login", "title");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Announcement not found!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementAlreadyBanned() {
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        User user = Mockito.mock(User.class);
        Mockito.when(announcement.getCreator())
                .thenReturn(user);
        Mockito.when(user.getLogin()).thenReturn("login");
        Mockito.when(Optional.of(announcement).get().getState())
                .thenReturn("BANNED");
        List<ValidationError> errors = validator.validate("login", "title");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Announcement already banned!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementHasOtherCreator() {
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        User user = Mockito.mock(User.class);
        Mockito.when(announcement.getCreator())
                .thenReturn(user);
        Mockito.when(user.getLogin()).thenReturn("login2");
        Mockito.when(Optional.of(announcement).get().getState())
                .thenReturn("ACTIVE");
        List<ValidationError> errors = validator.validate("login", "title");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect login of creator of announcement!");
    }

    @Test
    public void shouldReturnSuccess() {
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        User user = Mockito.mock(User.class);
        Mockito.when(announcement.getCreator())
                .thenReturn(user);
        Mockito.when(user.getLogin()).thenReturn("login");
        Mockito.when(Optional.of(announcement).get().getState())
                .thenReturn("ACTIVE");
        List<ValidationError> errors = validator.validate("login", "title");
        assertEquals(errors.size(), 0);
    }

}