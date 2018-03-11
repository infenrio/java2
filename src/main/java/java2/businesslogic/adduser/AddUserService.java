package java2.businesslogic.adduser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.models.User;
import java2.database.UserDatabase;

import java.util.List;

public class AddUserService {
    private UserDatabase userDatabase;
    private AddUserValidator addUserValidator;

    public AddUserService(UserDatabase userDatabase, AddUserValidator addUserValidator) {
        this.userDatabase = userDatabase;
        this.addUserValidator = addUserValidator;
    }

    public ServiceResponse addUser(String login, String name, String email) {
        List<ValidationError> validationErrors = addUserValidator.validate(login, name, email);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            User userToAdd = new User();
            userToAdd.setLogin(login);
            userToAdd.setName(name);
            userToAdd.setEmail(email);
            userDatabase.add(userToAdd);
            return new ServiceResponse(true, null);
        }
    }
}
