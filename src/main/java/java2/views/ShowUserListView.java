package java2.views;

import java2.database.User;
import java2.database.UserDatabase;

public class ShowUserListView implements View {
    private UserDatabase userDatabase;

    public ShowUserListView(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public void execute() {
        System.out.println("Registered users:");
        for (User user : userDatabase.getAllUsers()) {
            System.out.println(user);
        }
    }
}
