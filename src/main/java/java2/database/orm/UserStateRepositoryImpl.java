package java2.database.orm;

import java2.database.UserStateRepository;
import java2.domain.UserState;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
public class UserStateRepositoryImpl extends ORMRepository implements UserStateRepository {

    @Override
    public Optional<UserState> findById(String id) {
        CriteriaQuery<UserState> criteriaQuery = criteriaBuilder().createQuery(UserState.class);
        Root<UserState> root = criteriaQuery.from(UserState.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<UserState> q = session().createQuery(criteriaQuery);
        UserState userState = q.getSingleResult();
        return Optional.ofNullable(userState);
    }
}
