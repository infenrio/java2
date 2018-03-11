package java2.businesslogic.addannouncement;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.models.Announcement;
import java2.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class AddAnnouncementServiceTest {
    private AnnouncementDatabase announcementDatabase;
    private UserDatabase userDatabase;
    private AddAnnouncementValidator validator;
    private AddAnnouncementService service;

    @Before
    public void init() {
        announcementDatabase = Mockito.mock(AnnouncementDatabase.class);
        userDatabase = Mockito.mock(UserDatabase.class);
        validator = Mockito.mock(AddAnnouncementValidator.class);
        service = new AddAnnouncementService(announcementDatabase, userDatabase, validator);
    }

    @Test
    public void shouldReturnSuccess() {
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate("login", "title", "description")).
                thenReturn(errors);
        User user = Mockito.mock(User.class);
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        ServiceResponse response = service.addAnnouncement("login", "title", "description");
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementDatabase).add(any(Announcement.class));
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        User user = Mockito.mock(User.class);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        Mockito.when(userDatabase.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(validator.validate(null, "title", "description")).
                thenReturn(errors);
        ServiceResponse response = service.addAnnouncement(null, "title", "description");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(announcementDatabase);
    }
}