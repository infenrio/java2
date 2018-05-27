package java2.businesslogic.announcementban;

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
public class AnnouncementBanServiceImplTest {
    @Mock AnnouncementBanValidator validator;
    @Mock AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementBanService service = new AnnouncementBanServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        Announcement announcement = Mockito.mock(Announcement.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementBanResponse response = service.ban(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementRepository).banById(anyInt());
    }

    @Test
    public void shouldReturnFail() {
        AnnouncementBanRequest request = new AnnouncementBanRequest("login", 5);
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementBanResponse response = service.ban(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}