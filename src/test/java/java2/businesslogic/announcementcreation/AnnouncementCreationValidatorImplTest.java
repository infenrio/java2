package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.database.UserRepository;
import java2.domain.AnnouncementCategory;
import java2.domain.User;
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
public class AnnouncementCreationValidatorImplTest {
    @Mock private AnnouncementCategoryRepository announcementCategoryRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private AnnouncementCreationValidator validator = new AnnouncementCreationValidatorImpl();

    @Test
    public void shouldReturnSuccess() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", "description", "login");
        User user = Mockito.mock(User.class);
        AnnouncementCategory announcementCategory = Mockito.mock(AnnouncementCategory.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(announcementCategoryRepository.findById(1000)).
                thenReturn(Optional.of(announcementCategory));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenCategoryIdIsEmpty() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(0, "title", "description", "login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "categoryId");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, null, "description", "login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "title");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", null, "login");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "description");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", "description", null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotPresent() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", "description", "login");
        AnnouncementCategory announcementCategory = Mockito.mock(AnnouncementCategory.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.empty());
        Mockito.when(announcementCategoryRepository.findById(1000)).
                thenReturn(Optional.of(announcementCategory));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "User not found!");
    }

    @Test
    public void shouldReturnErrorWhenCategoryIsNotPresent() {
        AnnouncementCreationRequest request =
                new AnnouncementCreationRequest(1000, "title", "description", "login");
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findByLoginAndRole("login", 'U')).
                thenReturn(Optional.of(user));
        Mockito.when(announcementCategoryRepository.findById(1000)).
                thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "categoryId");
        assertEquals(errors.get(0).getErrorMessage(), "Category not found!");
    }
}