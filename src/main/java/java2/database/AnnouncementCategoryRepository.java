package java2.database;

import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;

import java.util.List;
import java.util.Optional;

public interface AnnouncementCategoryRepository {
    Optional<AnnouncementCategory> findById(int id);

    List<AnnouncementCategory> getAllCategories();

    List<AnnouncementCategory> getCategoriesByParentCategoryId(int parentCategoryId);
}
