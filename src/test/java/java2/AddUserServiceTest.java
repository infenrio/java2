package java2;

import java2.businesslogic.AddUserService;
import java2.database.UserDatabase;
import java2.database.UserInMemoryDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddUserServiceTest {
    private UserDatabase userDatabase;
    private AddUserService addUserService;

    @Before
    public void prepareService() {
        userDatabase = new UserInMemoryDatabase();
        addUserService = new AddUserService(userDatabase);
    }

    @Test
    public void newUserSuccessTest() {
        Assert.assertTrue(addUserService.addUser("login", "name", "email"));
    }

    @Test
    public void newUserFailTest() {
        Assert.assertTrue(addUserService.addUser("login", "name", "email"));
        Assert.assertFalse(addUserService.addUser("login", "name", "email"));
    }
}
