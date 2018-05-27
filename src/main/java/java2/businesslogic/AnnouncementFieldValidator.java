package java2.businesslogic;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnnouncementFieldValidator {
    @Autowired private AnnouncementRepository announcementRepository;

    public Optional<ValidationError> validateId(int id) {
        if(id == 0) {
            return Optional.of(new ValidationError("id", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateTitle(String title) {
        if(title == null || title.isEmpty()) {
            return Optional.of(new ValidationError("title", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateDescription(String description) {
        if(description == null || description.isEmpty()) {
            return Optional.of(new ValidationError("description", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateLogin(String login) {
        if(login == null || login.isEmpty()) {
            return Optional.of(new ValidationError("login", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateAnnouncementPresence(int id) {
        Optional<Announcement> announcementFound = announcementRepository.findById(id);
        if(!announcementFound.isPresent()) {
            return Optional.of(new ValidationError("id", "Announcement not found!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateAnnouncementAlreadyBanned(int id) {
        Optional<Announcement> foundAnnouncement = announcementRepository.findById(id);
        if(foundAnnouncement.isPresent()) {
            if(foundAnnouncement.get().getState().getId().equals("BANNED")) {
                return Optional.of(new ValidationError("id", "Announcement already banned!"));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> validateLoginOfCreator(String login, int id) {
        Optional<Announcement> foundAnnouncement = announcementRepository.findById(id);
        Announcement announcement = foundAnnouncement.get();
        if(!announcement.getCreator().getLogin().equals(login)) {
            return Optional.of(new ValidationError("login", "Incorrect login of creator of announcement!"));
        } else {
            return Optional.empty();
        }
    }
}
