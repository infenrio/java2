package java2.database.orm;

import java2.database.UserRepository;
import java2.database.UserStateRepository;
import java2.domain.User;
import java2.domain.UserState;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl extends ORMRepository implements UserRepository {
    @Autowired
    UserStateRepository userStateRepository;

    @Override
    public void save(User user) {
        session().save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<User> q = session().createQuery(criteriaQuery);
        User user;
        try {
            user = q.getSingleResult();
        } catch (NoResultException nre){
            user = null;
        }

//        User user = (User) criteriaBuilder().createQuery(User.class).from(User.class)
//                .add(Restrictions.eq("login", login))
//                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("login"), login));
        Query<User> q = session().createQuery(criteriaQuery);
        User user;
        try {
            user = q.getSingleResult();
        } catch (NoResultException nre){
            user = null;
        }
//        User user = (User) criteriaBuilder().createQuery(User.class).from(User.class)
//                .add(Restrictions.eq("login", login))
//                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("email"), email));
        Query<User> q = session().createQuery(criteriaQuery);
        User user;
        try {
            user = q.getSingleResult();
        } catch (NoResultException nre){
            user = null;
        }

//        User user = (User) criteriaBuilder().createQuery(User.class).from(User.class)
//                .add(Restrictions.eq("login", login))
//                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query<User> q = session().createQuery(criteriaQuery);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public void banByLogin(String login) {
        UserState bannedState = userStateRepository.findById("BANNED").get();

        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder().createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate.set("state", bannedState);
        criteriaUpdate.where(criteriaBuilder().equal(root.get("login"), login));

        // perform update
        session().createQuery(criteriaUpdate).executeUpdate();
    }
}
