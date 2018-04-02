package java2.database;

import java2.models.Announcement;
import java2.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementORMDatabase implements AnnouncementDatabase {
    @Autowired private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Announcement announcement) {

    }

    @Override
    public List<Announcement> findByUser(User user) {
        return null;
    }

    @Override
    public Optional<Announcement> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return null;
    }

    @Override
    public List<Announcement> getValidAnnouncements() {
        return null;
    }

    @Override
    public void banAnnouncement(Announcement announcement) {

    }
}
