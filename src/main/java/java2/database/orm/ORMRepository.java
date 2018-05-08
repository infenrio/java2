package java2.database.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

public class ORMRepository {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    protected CriteriaBuilder criteriaBuilder() {
        return session().getCriteriaBuilder();
    }
}
