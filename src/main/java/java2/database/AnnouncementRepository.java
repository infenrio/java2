package java2.database;

import java2.domain.Announcement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository {
    void save(Announcement announcement);

    List<Announcement> findByCategory(int id);

    List<Announcement> findByLogin(String login);

    Optional<Announcement> findByTitle(String title);

    List<Announcement> getAllAnnouncements();

    List<Announcement> getValidAnnouncements();

    void banByTitle(String title);
}
