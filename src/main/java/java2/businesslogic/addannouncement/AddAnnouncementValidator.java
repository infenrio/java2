package java2.businesslogic.addannouncement;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.models.Announcement;
import java2.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddAnnouncementValidator {
    private AnnouncementDatabase announcementDatabase;
    private UserDatabase userDatabase;

    public AddAnnouncementValidator(AnnouncementDatabase announcementDatabase, UserDatabase userDatabase) {
        this.announcementDatabase = announcementDatabase;
        this.userDatabase = userDatabase;
    }

    public List<ValidationError> validate(String login, String title, String description) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(login);
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = validateTitle(title);
        emptyTitleError.ifPresent(error -> errors.add(error));
        validateDescription(description).ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent()) {
            validateUserPresence(login).ifPresent(errors::add);
        }
        if(!emptyTitleError.isPresent()) {
            validateDuplicateTitle(title).ifPresent(errors::add);
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
            Optional<Announcement> announcementOpt = announcementDatabase.findByTitle(title);
            if(announcementOpt.isPresent()) {
                return Optional.of(new ValidationError("title", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateUserPresence(String login) {
        Optional<User> userFound = userDatabase.findByLogin(login);
        if(!userFound.isPresent()) {
            return Optional.of(new ValidationError("login", "User not found!"));
        } else {
            return Optional.empty();
        }
    }
}
