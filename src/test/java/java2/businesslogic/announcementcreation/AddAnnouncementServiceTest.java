package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.domain.Announcement;
import java2.domain.User;
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
public class AddAnnouncementServiceTest {
    @Mock private AnnouncementDatabase announcementDatabase;
    @Mock private UserDatabase userDatabase;
    @Mock private AddAnnouncementValidator validator;

    @InjectMocks
    private AddAnnouncementService service = new AddAnnouncementService();

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
        Mockito.when(validator.validate(null, "title", "description")).
                thenReturn(errors);
        ServiceResponse response = service.addAnnouncement(null, "title", "description");
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(announcementDatabase);
    }
}