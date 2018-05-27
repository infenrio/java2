package java2.database;

import java2.domain.Announcement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository {
    void save(Announcement announcement);

    void remove(Announcement announcement);

    Optional<Announcement> findById(int id);

    List<Announcement> findByCategory(int id);

    List<Announcement> findByLogin(String login);

    List<Announcement> findByTitle(String title);

    List<Announcement> findByDescription(String description);

    List<Announcement> getAllAnnouncements();

    List<Announcement> getValidAnnouncements();

    void banById(int id);

    void changeTitle(int id, String title);

    void changeDescription(int id, String description);
}
