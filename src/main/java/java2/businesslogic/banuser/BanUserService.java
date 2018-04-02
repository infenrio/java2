package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.UserDatabase;
import java2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BanUserService {
    @Autowired private UserDatabase userDatabase;
    @Autowired private BanUserValidator banUserValidator;

    public ServiceResponse banUser(String login) {
        List<ValidationError> validationErrors = banUserValidator.validate(login);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            Optional<User> foundUser = userDatabase.findByLogin(login);
            userDatabase.banUser(foundUser.get());
            return new ServiceResponse(true, null);
        }
    }
}
