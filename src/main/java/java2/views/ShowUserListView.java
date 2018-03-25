package java2.views;

import java2.models.User;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowUserListView implements View {
    @Autowired private UserDatabase userDatabase;

    @Override
    public void execute() {
        System.out.println("Registered users:");
        for (User user : userDatabase.getAllUsers()) {
            System.out.println(user);
        }
    }
}
