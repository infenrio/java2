package java2.businesslogic.addannouncement;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.models.Announcement;
import java2.database.AnnouncementDatabase;
import java2.models.User;
import java2.database.UserDatabase;

import java.util.List;
import java.util.Optional;

public class AddAnnouncementService {
    private AnnouncementDatabase announcementDatabase;
    private UserDatabase userDatabase;
    private AddAnnouncementValidator addAnnouncementValidator;

    public AddAnnouncementService(AnnouncementDatabase announcementDatabase, UserDatabase userDatabase,
                                  AddAnnouncementValidator addAnnouncementValidator) {
        this.announcementDatabase = announcementDatabase;
        this.userDatabase = userDatabase;
        this.addAnnouncementValidator = addAnnouncementValidator;
    }

    public ServiceResponse addAnnouncement(String login, String title, String description) {
        List<ValidationError> validationErrors = addAnnouncementValidator.validate(login, title, description);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            Optional<User> userFound = userDatabase.findByLogin(login);
            User user = userFound.get();
            Announcement newAnnouncement = new Announcement();
            newAnnouncement.setCreator(user);
            newAnnouncement.setTitle(title);
            newAnnouncement.setDescription(description);
            announcementDatabase.add(newAnnouncement);
            return new ServiceResponse(true, null);
        }
    }
}
