package java2.database.orm;

import java2.database.AnnouncementStateRepository;
import java2.domain.AnnouncementState;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
public class AnnouncementStateRepositoryImpl extends ORMRepository implements AnnouncementStateRepository {
    @Override
    public Optional<AnnouncementState> findById(String id) {
        CriteriaQuery<AnnouncementState> criteriaQuery = criteriaBuilder().createQuery(AnnouncementState.class);
        Root<AnnouncementState> root = criteriaQuery.from(AnnouncementState.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<AnnouncementState> q = session().createQuery(criteriaQuery);
        AnnouncementState announcementState = q.getSingleResult();
        return Optional.ofNullable(announcementState);
    }
}
