package java2.businesslogic;

import java2.database.UserDatabase;
import java2.models.User;
import java2.models.UserState;

import java.util.Optional;

public class BanUserService {

    private UserDatabase userDatabase;

    public BanUserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public String banUser(String login) {
        Optional<User> foundUser = userDatabase.findByLogin(login);
        if(!foundUser.isPresent()) {
            return "User does not exist!";
        } else {
            User user = foundUser.get();
            if(user.getState().equals(UserState.BANNED)) {
                return "User was already banned!";
            } else {
                user.ban();
                return "User was banned successfully!";
            }
        }
    }
}
