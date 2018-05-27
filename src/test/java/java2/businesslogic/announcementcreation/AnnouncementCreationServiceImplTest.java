package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.database.AnnouncementStateRepository;
import java2.database.UserRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import java2.domain.AnnouncementState;
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
public class AnnouncementCreationServiceImplTest {
    @Mock private AnnouncementRepository announcementRepository;
    @Mock private UserRepository userRepository;
    @Mock private AnnouncementCreationValidator validator;
    @Mock private AnnouncementStateRepository announcementStateRepository;
    @Mock private AnnouncementCategoryRepository announcementCategoryRepository;

    @InjectMocks
    private AnnouncementCreationService service = new AnnouncementCreationServiceImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", "description", "login");
        AnnouncementState announcementState = Mockito.mock(AnnouncementState.class);
        AnnouncementCategory announcementCategory = Mockito.mock(AnnouncementCategory.class);
        User user = Mockito.mock(User.class);
        List<ValidationError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        Mockito.when(announcementStateRepository.findById("ACTIVE")).
                thenReturn(Optional.of(announcementState));
        Mockito.when(userRepository.findByLogin("login")).
                thenReturn(Optional.of(user));
        Mockito.when(announcementCategoryRepository.findById(1000)).
                thenReturn(Optional.of(announcementCategory));
        AnnouncementCreationResponse response = service.create(request);
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
        Mockito.verify(announcementRepository).save(any(Announcement.class));
    }

    @Test
    public void shouldReturnFail() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, null, "description", "login");
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "ValidationError message"));
        Mockito.when(validator.validate(request)).
                thenReturn(errors);
        AnnouncementCreationResponse response = service.create(request);
        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
        Mockito.verifyZeroInteractions(announcementRepository);
    }
}