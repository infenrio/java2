package java2.database;

import java2.configs.SpringAppConfig;
import java2.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAppConfig.class })
@Transactional
public class UserORMDatabaseTest {
    @Autowired private UserDatabase database;

    @Test
    public void shouldAddUserToDatabase() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setName("name");
        user.setEmail("email");
        user.setState("ACTIVE");

        assertEquals(user.getId(), 0);

        database.add(user);

        assertNotEquals(user.getId(), 0);
    }
}