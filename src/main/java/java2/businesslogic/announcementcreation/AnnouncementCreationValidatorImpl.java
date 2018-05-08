package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import java2.database.UserRepository;
import java2.domain.Announcement;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementCreationValidatorImpl implements AnnouncementCreationValidator {
    @Autowired private AnnouncementRepository announcementRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(AnnouncementCreationRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = validateTitle(request.getTitle());
        emptyTitleError.ifPresent(error -> errors.add(error));
        validateDescription(request.getDescription()).ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent()) {
            validateUserPresence(request.getLogin()).ifPresent(errors::add);
        }
        if(!emptyTitleError.isPresent()) {
            validateDuplicateTitle(request.getTitle()).ifPresent(errors::add);
        }
        return errors;
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

    private Optional<ValidationError> validateDuplicateTitle(String title) {
        if(title != null && !title.isEmpty()) {
            Optional<Announcement> announcementOpt = announcementRepository.findByTitle(title);
            if(announcementOpt.isPresent()) {
                return Optional.of(new ValidationError("title", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateUserPresence(String login) {
        Optional<User> userFound = userRepository.findByLogin(login);
        if(!userFound.isPresent()) {
            return Optional.of(new ValidationError("login", "User not found!"));
        } else {
            return Optional.empty();
        }
    }
}
