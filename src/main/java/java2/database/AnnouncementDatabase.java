package java2.database;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDatabase {
    void add(Announcement announcement);

    List<Announcement> findByUser(User user);

    Optional<Announcement> findByTitle(String title);

    List<Announcement> getAllAnnouncements();
}
