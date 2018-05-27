package java2.database;

import java2.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndRole(String login, char role);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

    void banByLogin(String login);
}
