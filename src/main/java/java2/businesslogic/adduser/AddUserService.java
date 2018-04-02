package java2.businesslogic.adduser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.models.User;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {
    @Autowired private UserDatabase userDatabase;
    @Autowired private AddUserValidator addUserValidator;

    public ServiceResponse addUser(String login, String password, String name, String email) {
        List<ValidationError> validationErrors = addUserValidator.validate(login, password, name, email);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            User userToAdd = new User();
            userToAdd.setLogin(login);
            userToAdd.setPassword(password);
            userToAdd.setName(name);
            userToAdd.setEmail(email);
            userToAdd.setState("ACTIVE");
            userDatabase.add(userToAdd);
            return new ServiceResponse(true, null);
        }
    }
}
