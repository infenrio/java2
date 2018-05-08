package java2.businesslogic.announcementban;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.ValidationError;
import java2.database.AnnouncementDatabase;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class BanAnnouncementService {
    @Autowired private AnnouncementDatabase announcementDatabase;
    @Autowired private BanAnnouncementValidator banAnnouncementValidator;

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
