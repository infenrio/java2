package java2.businesslogic.announcementediting;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
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
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class AnnouncementEditingServiceImplTest {
    @Mock private AnnouncementEditingValidator validator;
    @Mock private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementEditingService service = new AnnouncementEditingServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
        Announcement announcement = Mockito.mock(Announcement.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementEditingResponse response = service.edit(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementRepository).changeTitle(anyInt(), any(String.class));
        Mockito.verify(announcementRepository).changeDescription(anyInt(), any(String.class));
    }

    @Test
    public void shouldReturnFail() {
        AnnouncementEditingRequest request =
                new AnnouncementEditingRequest(5, "title", "description", "login");
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementEditingResponse response = service.edit(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}