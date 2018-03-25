package java2.businesslogic.adduser;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
import java2.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserValidator {
    private UserDatabase userDatabase;

    public AddUserValidator(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public List<ValidationError> validate(String login, String name, String email) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(login);
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyEmailError = validateEmail(email);
        emptyEmailError.ifPresent(error -> errors.add(error));
        validateName(name).ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent()) {
            validateDuplicateLogin(login).ifPresent(errors::add);
        }
        if(!emptyEmailError.isPresent()) {
            validateDuplicateEmail(email).ifPresent(errors::add);
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

    private Optional<ValidationError> validateName(String name) {
        if(name == null || name.isEmpty()) {
            return Optional.of(new ValidationError("name", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateEmail(String email) {
        if(email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "Must not be empty!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateLogin(String login) {
        if(login != null && !login.isEmpty()) {
            Optional<User> userOpt = userDatabase.findByLogin(login);
            if(userOpt.isPresent()) {
                return Optional.of(new ValidationError("login", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateDuplicateEmail(String email) {
        if(email != null && !email.isEmpty()) {
            Optional<User> userOpt = userDatabase.findByEmail(email);
            if(userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }
}
