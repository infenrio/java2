package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.domain.Announcement;
import java2.database.AnnouncementDatabase;
import java2.domain.User;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class AddAnnouncementService {
    @Autowired private AnnouncementDatabase announcementDatabase;
    @Autowired private UserDatabase userDatabase;
    @Autowired private AddAnnouncementValidator addAnnouncementValidator;

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
