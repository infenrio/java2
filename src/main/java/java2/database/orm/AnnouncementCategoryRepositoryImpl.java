package java2.database.orm;

import java2.database.AnnouncementCategoryRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementCategoryRepositoryImpl extends ORMRepository implements AnnouncementCategoryRepository {
    @Override
    public Optional<AnnouncementCategory> findById(int id) {
        CriteriaQuery<AnnouncementCategory> criteriaQuery = criteriaBuilder().createQuery(AnnouncementCategory.class);
        Root<AnnouncementCategory> root = criteriaQuery.from(AnnouncementCategory.class);
        criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("id"), id));
        Query<AnnouncementCategory> q = session().createQuery(criteriaQuery);
        try {
            AnnouncementCategory announcementCategory = q.getSingleResult();
            return Optional.ofNullable(announcementCategory);
        } catch(NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<AnnouncementCategory> getAllCategories() {
        CriteriaQuery<AnnouncementCategory> criteriaQuery = criteriaBuilder().createQuery(AnnouncementCategory.class);
        Root<AnnouncementCategory> root = criteriaQuery.from(AnnouncementCategory.class);
        criteriaQuery.select(root);
        Query<AnnouncementCategory> q = session().createQuery(criteriaQuery);
        List<AnnouncementCategory> announcementCategories = q.getResultList();
        return announcementCategories;
    }

    @Override
    @Transactional
    public List<AnnouncementCategory> getCategoriesByParentCategoryId(int parentCategoryId) {
        Optional<AnnouncementCategory> parentCategory = findById(parentCategoryId);

        CriteriaQuery<AnnouncementCategory> criteriaQuery = criteriaBuilder().createQuery(AnnouncementCategory.class);
        Root<AnnouncementCategory> root = criteriaQuery.from(AnnouncementCategory.class);
        if(parentCategory.isPresent()) {
            criteriaQuery.select(root).where(criteriaBuilder().equal(root.get("parentCategory"), parentCategory.get()));
        } else {
            criteriaQuery.select(root).where(criteriaBuilder().isNull(root.get("parentCategory")));
        }
        Query<AnnouncementCategory> q = session().createQuery(criteriaQuery);
        List<AnnouncementCategory> announcementCategories = q.getResultList();
        return announcementCategories;
    }
}
