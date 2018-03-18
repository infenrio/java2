package java2.businesslogic.banuser;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
import java2.models.User;
import java2.models.UserState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BanUserValidator {
    private UserDatabase userDatabase;

    public BanUserValidator(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public List<ValidationError> validate(String login) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(login);
        emptyLoginError.ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent()) {
            validateUserPresence(login).ifPresent(errors::add);
        }
        if(!emptyLoginError.isPresent()) {
            validateUserAlreadyBanned(login).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<ValidationError> validateLogin(String login) {
        if(login == null || login.isEmpty()) {
            return Optional.of(new ValidationError("login", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateUserPresence(String login) {
        Optional<User> userFound = userDatabase.findByLogin(login);
        if(!userFound.isPresent()) {
            return Optional.of(new ValidationError("login", "User not found!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateUserAlreadyBanned(String login) {
        Optional<User> userFound = userDatabase.findByLogin(login);
        if(userFound.isPresent()) {
            if(userFound.get().getState().equals("BANNED")) {
                return Optional.of(new ValidationError("login", "User already banned!"));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
