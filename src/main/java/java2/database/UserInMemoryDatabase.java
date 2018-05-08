package java2.database;

import java2.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInMemoryDatabase implements UserDatabase {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(users);
        return allUsers;
    }

    @Override
    public void banUser(User user) {

    }


}
