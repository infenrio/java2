package java2.businesslogic;

import java2.models.Announcement;
import java2.database.AnnouncementDatabase;
import java2.models.User;
import java2.database.UserDatabase;

import java.util.Optional;

public class AddAnnouncementService {
    private AnnouncementDatabase announcementDatabase;
    private UserDatabase userDatabase;

    public AddAnnouncementService(AnnouncementDatabase announcementDatabase, UserDatabase userDatabase) {
        this.announcementDatabase = announcementDatabase;
        this.userDatabase = userDatabase;
    }

    public boolean addAnnouncement(String login, String title, String description) {
        Optional<User> userFound = userDatabase.findByLogin(login);
        if(!userFound.isPresent()) {
            return false;
        } else {
            User user = userFound.get();
            Announcement announcement = new Announcement();
            announcement.setCreator(user);
            announcement.setTitle(title);
            announcement.setDescription(description);
            announcementDatabase.add(announcement);
            return true;
        }
    }
}
