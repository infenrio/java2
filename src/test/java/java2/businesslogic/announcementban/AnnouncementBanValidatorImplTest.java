package java2.businesslogic.announcementban;

import java2.businesslogic.AnnouncementFieldValidator;
import java2.businesslogic.ValidationError;
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
public class AnnouncementBanValidatorImplTest {
    @Mock
    private AnnouncementFieldValidator fieldValidator;

    @InjectMocks
    private AnnouncementBanValidator validator = new AnnouncementBanValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        AnnouncementBanRequest request = new AnnouncementBanRequest(null, 5);
        ValidationError error = new ValidationError("login", "Must not be empty!");
        Mockito.when(fieldValidator.validateLogin(request.getLogin())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenIdIsEmpty() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 0);
        ValidationError error = new ValidationError("id", "Must not be empty!");
        Mockito.when(fieldValidator.validateId(request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementIsNotPresent() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        ValidationError error = new ValidationError("id", "Announcement not found!");
        Mockito.when(fieldValidator.validateAnnouncementPresence(request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Announcement not found!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementAlreadyBanned() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        ValidationError error = new ValidationError("id", "Announcement already banned!");
        Mockito.when(fieldValidator.validateAnnouncementAlreadyBanned(request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Announcement already banned!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementHasOtherCreator() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        ValidationError error = new ValidationError("login", "Incorrect login of creator of announcement!");
        Mockito.when(fieldValidator.validateLoginOfCreator(request.getLogin(), request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect login of creator of announcement!");
    }
}