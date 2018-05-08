package java2.database;

import java2.domain.UserState;

import java.util.Optional;

public interface UserStateRepository {
    Optional<UserState> findById(String id);
}
