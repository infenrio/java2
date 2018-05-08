package java2.database;

import java2.domain.AnnouncementCategory;

import java.util.Optional;

public interface AnnouncementCategoryRepository {
    Optional<AnnouncementCategory> findById(int id);
}
