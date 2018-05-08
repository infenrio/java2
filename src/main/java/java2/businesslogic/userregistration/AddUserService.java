package java2.businesslogic.userregistration;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.domain.User;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
public class AddUserService {
    @Autowired private UserDatabase userDatabase;
    @Autowired private AddUserValidator addUserValidator;

    @Transactional
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
            userDatabase.add(userToAdd);
            return new ServiceResponse(true, null);
        }
    }
}
