package java2.businesslogic;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementState;
import java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AnnouncementFieldValidatorTest {
    @Mock private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementFieldValidator validator = new AnnouncementFieldValidator();

    @Test
    public void validateIdShouldReturnSuccess() {
        Optional<ValidationError> error = validator.validateId(5);
        assertFalse(error.isPresent());
    }

    @Test
    public void validateIdShouldReturnFail() {
        Optional<ValidationError> error = validator.validateId(0);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "id");
        assertEquals(error.get().getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void validateTitleShouldReturnSuccess() {
        Optional<ValidationError> error = validator.validateTitle("title");
        assertFalse(error.isPresent());
    }

    @Test
    public void validateTitleShouldReturnFail() {
        Optional<ValidationError> error = validator.validateTitle(null);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "title");
        assertEquals(error.get().getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void validateDescriptionShouldReturnSuccess() {
        Optional<ValidationError> error = validator.validateDescription("description");
        assertFalse(error.isPresent());
    }

    @Test
    public void validateDescriptionShouldReturnFail() {
        Optional<ValidationError> error = validator.validateDescription(null);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "description");
        assertEquals(error.get().getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void validateLoginShouldReturnSuccess() {
        Optional<ValidationError> error = validator.validateLogin("login");
        assertFalse(error.isPresent());
    }

    @Test
    public void validateLoginShouldReturnFail() {
        Optional<ValidationError> error = validator.validateLogin(null);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "login");
        assertEquals(error.get().getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void validateAnnouncementPresenceShouldReturnSuccess() {
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        Optional<ValidationError> error = validator.validateAnnouncementPresence(5);
        assertFalse(error.isPresent());
    }

    @Test
    public void validateAnnouncementPresenceShouldReturnFail() {
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.empty());
        Optional<ValidationError> error = validator.validateAnnouncementPresence(5);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "id");
        assertEquals(error.get().getErrorMessage(), "Announcement not found!");
    }

    @Test
    public void validateAnnouncementAlreadyBannedShouldReturnSuccess() {
        Announcement announcement = Mockito.mock(Announcement.class);
        AnnouncementState announcementState = Mockito.mock(AnnouncementState.class);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        Mockito.when(announcement.getState()).thenReturn(announcementState);
        Mockito.when(announcementState.getId()).thenReturn("ACTIVE");
        Optional<ValidationError> error = validator.validateAnnouncementAlreadyBanned(5);
        assertFalse(error.isPresent());
    }

    @Test
    public void validateAnnouncementAlreadyBannedShouldReturnFail() {
        Announcement announcement = Mockito.mock(Announcement.class);
        AnnouncementState announcementState = Mockito.mock(AnnouncementState.class);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        Mockito.when(announcement.getState()).thenReturn(announcementState);
        Mockito.when(announcementState.getId()).thenReturn("BANNED");
        Optional<ValidationError> error = validator.validateAnnouncementAlreadyBanned(5);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "id");
        assertEquals(error.get().getErrorMessage(), "Announcement already banned!");
    }

    @Test
    public void validateLoginOfCreatorShouldReturnSuccess() {
        Announcement announcement = Mockito.mock(Announcement.class);
        User user = Mockito.mock(User.class);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        Mockito.when(announcement.getCreator()).thenReturn(user);
        Mockito.when(user.getLogin()).thenReturn("login");
        Optional<ValidationError> error = validator.validateLoginOfCreator("login",5);
        assertFalse(error.isPresent());
    }

    @Test
    public void validateLoginOfCreatorShouldReturnFail() {
        Announcement announcement = Mockito.mock(Announcement.class);
        User user = Mockito.mock(User.class);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        Mockito.when(announcement.getCreator()).thenReturn(user);
        Mockito.when(user.getLogin()).thenReturn("login2");
        Optional<ValidationError> error = validator.validateLoginOfCreator("login",5);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "login");
        assertEquals(error.get().getErrorMessage(), "Incorrect login of creator of announcement!");
    }
}