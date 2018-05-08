package java2.businesslogic.announcementban;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementBanValidatorImpl implements AnnouncementBanValidator {
    @Autowired private AnnouncementRepository announcementRepository;

    public List<ValidationError> validate(AnnouncementBanRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = validateTitle(request.getTitle());
        emptyTitleError.ifPresent(error -> errors.add(error));
        if(!emptyTitleError.isPresent()) {
            Optional<ValidationError> announcementPresenceError = validateAnnouncementPresence(request.getTitle());
            announcementPresenceError.ifPresent(errors::add);
            if(!announcementPresenceError.isPresent()) {
                validateAnnouncementAlreadyBanned(request.getTitle()).ifPresent(errors::add);
                if (!emptyLoginError.isPresent()) {
                    validateLoginOfCreator(request.getLogin(), request.getTitle()).ifPresent(errors::add);
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
        Optional<Announcement> announcementFound = announcementRepository.findByTitle(title);
        if(!announcementFound.isPresent()) {
            return Optional.of(new ValidationError("title", "Announcement not found!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateAnnouncementAlreadyBanned(String title) {
        Optional<Announcement> foundAnnouncement = announcementRepository.findByTitle(title);
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
        Optional<Announcement> foundAnnouncement = announcementRepository.findByTitle(title);
        Announcement announcement = foundAnnouncement.get();
        if(!announcement.getCreator().getLogin().equals(login)) {
            return Optional.of(new ValidationError("login", "Incorrect login of creator of announcement!"));
        } else {
            return Optional.empty();
        }
    }
}
