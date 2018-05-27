package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.database.UserRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementCreationValidatorImpl implements AnnouncementCreationValidator {
    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(AnnouncementCreationRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyCategoryIdError = validateCategory(request.getCategoryId());
        emptyCategoryIdError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = validateTitle(request.getTitle());
        emptyTitleError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyDescriptionError = validateDescription(request.getDescription());
        emptyDescriptionError.ifPresent(error -> errors.add(error));
        if(errors.size() == 0) {
            validateCategoryPresence(request.getCategoryId()).ifPresent(errors::add);
            validateUserPresence(request.getLogin()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<ValidationError> validateCategory(int categoryId) {
        if(categoryId == 0) {
            return Optional.of(new ValidationError("categoryId", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateLogin(String login) {
        if(login == null || login.isEmpty()) {
            return Optional.of(new ValidationError("login", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateTitle(String title) {
        if(title == null || title.isEmpty()) {
            return Optional.of(new ValidationError("title", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDescription(String description) {
        if(description == null || description.isEmpty()) {
            return Optional.of(new ValidationError("description", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateCategoryPresence(int categoryId) {
        Optional<AnnouncementCategory> categoryFound = announcementCategoryRepository.findById(categoryId);
        if(!categoryFound.isPresent()) {
            return Optional.of(new ValidationError("categoryId", "Category not found!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateUserPresence(String login) {
        Optional<User> userFound = userRepository.findByLoginAndRole(login, 'U');
        if(!userFound.isPresent()) {
            return Optional.of(new ValidationError("login", "User not found!"));
        } else {
            return Optional.empty();
        }
    }
}
