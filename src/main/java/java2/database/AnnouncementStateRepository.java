package java2.database;

import java2.domain.AnnouncementState;

import java.util.Optional;

public interface AnnouncementStateRepository {
    Optional<AnnouncementState> findById(String id);
}
