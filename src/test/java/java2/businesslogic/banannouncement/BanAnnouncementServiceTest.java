package java2.businesslogic.banannouncement;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.models.Announcement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BanAnnouncementServiceTest {
    @Mock private AnnouncementDatabase announcementDatabase;
    @Mock private BanAnnouncementValidator validator;

    @InjectMocks
    private BanAnnouncementService service = new BanAnnouncementService();

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
        ServiceResponse response = service.banAnnouncement(null, "title");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}