package java2.businesslogic.announcementremoval;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
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
public class AnnouncementRemovalServiceImplTest {
    @Mock AnnouncementRemovalValidator validator;
    @Mock AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementRemovalService service = new AnnouncementRemovalServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementRemovalRequest request = new AnnouncementRemovalRequest("login", 5);
        Announcement announcement = Mockito.mock(Announcement.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        Mockito.when(announcementRepository.findById(5)).
                thenReturn(Optional.of(announcement));
        AnnouncementRemovalResponse response = service.remove(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementRepository).remove(any(Announcement.class));
    }

    @Test
    public void shouldReturnFail() {
        AnnouncementRemovalRequest request = new AnnouncementRemovalRequest("login", 5);
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementRemovalResponse response = service.remove(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}