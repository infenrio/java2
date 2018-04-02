package java2.database;

import java2.models.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRealDatabaseTest {

    private UserRealDatabase database = new UserRealDatabase();

    @Test
    public void shouldAddUserToDatabase() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setName("name");
        user.setEmail("email");
        user.setState("ACTIVE");

        database.add(user);

        assertNotEquals(user.getId(), 0);
    }

}