package java2.views;

import java2.database.UserRepository;
import java2.domain.User;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowUserListView implements View {
    @Autowired private UserRepository userRepository;

    @Override
    public void execute() {
        System.out.println("Registered users:");
        for (User user : userRepository.getAllUsers()) {
            System.out.println(user);
        }
    }
}
