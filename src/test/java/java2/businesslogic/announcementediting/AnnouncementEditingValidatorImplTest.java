package java2.businesslogic.announcementediting;

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
public class AnnouncementEditingValidatorImplTest {
    @Mock
    private AnnouncementFieldValidator fieldValidator;

    @InjectMocks
    private AnnouncementEditingValidator validator = new AnnouncementEditingValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenIdIsEmpty() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(0, "title", "description", "login");
        ValidationError error = new ValidationError("id", "Must not be empty!");
        Mockito.when(fieldValidator.validateId(request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, null, "description", "login");
        ValidationError error = new ValidationError("title", "Must not be empty!");
        Mockito.when(fieldValidator.validateTitle(request.getTitle())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", null, "login");
        ValidationError error = new ValidationError("description", "Must not be empty!");
        Mockito.when(fieldValidator.validateDescription(request.getDescription())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", null);
        ValidationError error = new ValidationError("login", "Must not be empty!");
        Mockito.when(fieldValidator.validateLogin(request.getLogin())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAnnouncementIsNotPresent() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
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
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
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
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
        ValidationError error = new ValidationError("login", "Incorrect login of creator of announcement!");
        Mockito.when(fieldValidator.validateLoginOfCreator(request.getLogin(), request.getId())).
                thenReturn(Optional.of(error));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect login of creator of announcement!");
    }
}