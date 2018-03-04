package java2.businesslogic;

import java2.database.User;
import java2.database.UserDatabase;

public class AddUserService {
    private UserDatabase userDatabase;

    public AddUserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public boolean addUser(String login, String name, String email) {
        if(userDatabase.findByLogin(login).isPresent()) {
            return false;
        } else {
            User userToAdd = new User();
            userToAdd.setLogin(login);
            userToAdd.setName(name);
            userToAdd.setEmail(email);
            userDatabase.add(userToAdd);
            return true;
        }
    }
}
