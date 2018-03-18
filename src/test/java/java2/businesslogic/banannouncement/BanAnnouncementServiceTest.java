package java2.businesslogic.banannouncement;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.models.Announcement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class BanAnnouncementServiceTest {
    private AnnouncementDatabase announcementDatabase;
    private BanAnnouncementValidator validator;
    private BanAnnouncementService service;

    @Before
    public void init() {
        announcementDatabase = Mockito.mock(AnnouncementDatabase.class);
        validator = Mockito.mock(BanAnnouncementValidator.class);
        service = new BanAnnouncementService(announcementDatabase, validator);
    }

    @Test
    public void shouldReturnSuccess() {
        List<ValidationError> errors = new ArrayList<>();
        Announcement announcement = Mockito.mock(Announcement.class);
        Mockito.when(validator.validate("login", "title")).
                thenReturn(errors);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.of(announcement));
        ServiceResponse response = service.banAnnouncement("login", "title");
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementDatabase).banAnnouncement(any(Announcement.class));
    }

    @Test
    public void shouldReturnFail() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(null, "title")).
                thenReturn(errors);
        Mockito.when(announcementDatabase.findByTitle("title")).
                thenReturn(Optional.empty());
        ServiceResponse response = service.banAnnouncement(null, "title");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}