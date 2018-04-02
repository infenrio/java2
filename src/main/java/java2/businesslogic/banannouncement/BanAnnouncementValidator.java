package java2.businesslogic.banannouncement;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.models.Announcement;
import java2.models.AnnouncementState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BanAnnouncementValidator {
    @Autowired private AnnouncementDatabase announcementDatabase;

    public List<ValidationError> validate(String login, String title) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(login);
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = validateTitle(title);
        emptyTitleError.ifPresent(error -> errors.add(error));
        if(!emptyTitleError.isPresent()) {
            Optional<ValidationError> announcementPresenceError = validateAnnouncementPresence(title);
            announcementPresenceError.ifPresent(errors::add);
            if(!announcementPresenceError.isPresent()) {
                validateAnnouncementAlreadyBanned(title).ifPresent(errors::add);
                if (!emptyLoginError.isPresent()) {
                    validateLoginOfCreator(login, title).ifPresent(errors::add);
                }
            }
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

    private Optional<ValidationError> validateAnnouncementPresence(String title) {
        Optional<Announcement> announcementFound = announcementDatabase.findByTitle(title);
        if(!announcementFound.isPresent()) {
            return Optional.of(new ValidationError("title", "Announcement not found!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateAnnouncementAlreadyBanned(String title) {
        Optional<Announcement> foundAnnouncement = announcementDatabase.findByTitle(title);
        if(foundAnnouncement.isPresent()) {
            if(foundAnnouncement.get().getState().equals("BANNED")) {
                return Optional.of(new ValidationError("title", "Announcement already banned!"));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateLoginOfCreator(String login, String title) {
        Optional<Announcement> foundAnnouncement = announcementDatabase.findByTitle(title);
        Announcement announcement = foundAnnouncement.get();
        if(!announcement.getCreator().getLogin().equals(login)) {
            return Optional.of(new ValidationError("login", "Incorrect login of creator of announcement!"));
        } else {
            return Optional.empty();
        }
    }
}
