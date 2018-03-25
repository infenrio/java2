package java2.businesslogic.banannouncement;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.models.Announcement;
import java2.models.AnnouncementState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BanAnnouncementService {
    private AnnouncementDatabase announcementDatabase;
    private BanAnnouncementValidator banAnnouncementValidator;

    public BanAnnouncementService(AnnouncementDatabase announcementDatabase, BanAnnouncementValidator banAnnouncementValidator) {
        this.announcementDatabase = announcementDatabase;
        this.banAnnouncementValidator = banAnnouncementValidator;
    }

    public ServiceResponse banAnnouncement(String login, String title) {
        List<ValidationError> validationErrors = banAnnouncementValidator.validate(login, title);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            Optional<Announcement> foundAnnouncement = announcementDatabase.findByTitle(title);
            announcementDatabase.banAnnouncement(foundAnnouncement.get());
            return new ServiceResponse(true, null);
        }
    }
}
