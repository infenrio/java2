package java2.database;

import java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

//@Component
public class UserORMDatabase implements UserDatabase {
    @Autowired private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private CriteriaBuilder criteriaBuilder() {
        return session().getCriteriaBuilder();
    }

    @Override
    public void add(User user) {
        session().save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        return session().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void banUser(User user) {

    }
}
