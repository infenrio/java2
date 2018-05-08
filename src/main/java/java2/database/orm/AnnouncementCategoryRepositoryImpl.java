package java2.database.orm;

import java2.database.AnnouncementCategoryRepository;
import java2.domain.AnnouncementCategory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
public class AnnouncementCategoryRepositoryImpl extends ORMRepository implements AnnouncementCategoryRepository {
    @Override
    public Optional<AnnouncementCategory> findById(int id) {
        CriteriaQuery<AnnouncementCategory> criteriaQuery = criteriaBuilder().createQuery(AnnouncementCategory.class);
        Root<AnnouncementCategory> root = criteriaQuery.from(AnnouncementCategory.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<AnnouncementCategory> q = session().createQuery(criteriaQuery);
        AnnouncementCategory announcementCategory = q.getSingleResult();
        return Optional.ofNullable(announcementCategory);
    }
}
