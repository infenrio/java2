package java2.database;

import java2.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {
    void add(User user);

    Optional<User> findByLogin(String login);

    List<User> getAllUsers();
}
