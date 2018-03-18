package java2.database;

import java2.models.Announcement;
import java2.models.User;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDatabase {
    void add(Announcement announcement);

    List<Announcement> findByUser(User user);

    Optional<Announcement> findByTitle(String title);

    List<Announcement> getAllAnnouncements();

    List<Announcement> getValidAnnouncements();

    void banAnnouncement(Announcement announcement);
}
