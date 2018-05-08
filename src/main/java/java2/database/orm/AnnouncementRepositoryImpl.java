package java2.database.orm;

import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.database.AnnouncementStateRepository;
import java2.database.UserStateRepository;
import java2.domain.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementRepositoryImpl extends ORMRepository implements AnnouncementRepository {
    @Autowired UserStateRepository userStateRepository;
    @Autowired AnnouncementStateRepository announcementStateRepository;

    @Override
    public void save(Announcement announcement) {
        session().save(announcement);
    }

    @Override
    @Transactional
    public List<Announcement> findByCategory(int id) {
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder().createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        Join<Announcement, AnnouncementCategory> categories = root.join("category");
        criteriaQuery.select(root).where(criteriaBuilder().equal(categories.get("id"), id));
        Query<Announcement> q = session().createQuery(criteriaQuery);
        List<Announcement> announcements = q.getResultList();
        return announcements;
    }

    @Override
    public List<Announcement> findByLogin(String login) {
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder().createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        Join<Announcement, User> users = root.join("creator");
        criteriaQuery.select(root).where(criteriaBuilder().equal(users.get("login"), login));
        Query<Announcement> q = session().createQuery(criteriaQuery);
        List<Announcement> announcements = q.getResultList();
        return announcements;
    }

    @Override
    public Optional<Announcement> findByTitle(String title) {
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder().createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("title"), title));
        Query<Announcement> q = session().createQuery(criteriaQuery);
        Announcement announcement;
        try {
            announcement = q.getSingleResult();
        } catch (NoResultException nre){
            announcement = null;
        }
        return Optional.ofNullable(announcement);
    }

    @Override
    @Transactional
    public List<Announcement> getAllAnnouncements() {
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder().createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        criteriaQuery.select(root);
        Query<Announcement> q = session().createQuery(criteriaQuery);
        List<Announcement> announcements = q.getResultList();
        return announcements;
    }

    @Override
    @Transactional
    public List<Announcement> getValidAnnouncements() {
        UserState bannedUserState = userStateRepository.findById("BANNED").get();
        AnnouncementState bannedAnnouncementState = announcementStateRepository.findById("BANNED").get();

        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder().createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        Join<Announcement, User> users = root.join("creator");
        criteriaQuery.select(root).where(criteriaBuilder().and(
                criteriaBuilder().notEqual(root.get("state"), bannedAnnouncementState),
                criteriaBuilder().notEqual(users.get("state"), bannedUserState)));
        Query<Announcement> q = session().createQuery(criteriaQuery);
        List<Announcement> announcements = q.getResultList();
        return announcements;
    }

    @Override
    public void banByTitle(String title) {
        AnnouncementState bannedState = announcementStateRepository.findById("BANNED").get();

        CriteriaUpdate<Announcement> criteriaUpdate = criteriaBuilder().createCriteriaUpdate(Announcement.class);
        Root<Announcement> root = criteriaUpdate.from(Announcement.class);
        criteriaUpdate.set("state", bannedState);
        criteriaUpdate.where(criteriaBuilder().equal(root.get("title"), title));

        // perform update
        session().createQuery(criteriaUpdate).executeUpdate();
    }
}
