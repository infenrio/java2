package java2.database;

import java2.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {
    void add(User user);

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

    void banUser(User user);
}
