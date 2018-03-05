package java2.businesslogic;

import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import java2.models.Announcement;
import java2.models.AnnouncementState;

import java.util.Optional;

public class BanAnnouncementService {
    private AnnouncementDatabase announcementDatabase;

    public BanAnnouncementService(AnnouncementDatabase announcementDatabase) {
        this.announcementDatabase = announcementDatabase;
    }

    public String banAnnouncement(String login, String title) {
        Optional<Announcement> foundAnnouncement = announcementDatabase.findByTitle(title);
        if(!foundAnnouncement.isPresent()) {
            return "Announcement does not exist!";
        } else {
            Announcement announcement = foundAnnouncement.get();
            if(!announcement.getCreator().getLogin().equals(login)) {
                return "Incorrect login of creator of announcement!";
            } else if(announcement.getState().equals(AnnouncementState.BANNED)) {
                return "Announcement was already banned!";
            } else {
                announcement.ban();
                return "Announcement was banned successfully!";
            }
        }
    }
}
