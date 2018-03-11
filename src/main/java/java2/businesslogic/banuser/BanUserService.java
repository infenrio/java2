package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.businesslogic.ServiceResponse;
import java2.database.UserDatabase;
import java2.models.User;

import java.util.List;
import java.util.Optional;

public class BanUserService {
    private UserDatabase userDatabase;
    private BanUserValidator banUserValidator;

    public BanUserService(UserDatabase userDatabase, BanUserValidator banUserValidator) {
        this.userDatabase = userDatabase;
        this.banUserValidator = banUserValidator;
    }

    public ServiceResponse banUser(String login) {
        List<ValidationError> validationErrors = banUserValidator.validate(login);
        if(!validationErrors.isEmpty()) {
            return new ServiceResponse(false, validationErrors);
        } else {
            Optional<User> foundUser = userDatabase.findByLogin(login);
            foundUser.get().ban();
            return new ServiceResponse(true, null);
        }
    }
}
