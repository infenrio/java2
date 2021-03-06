package java2.businesslogic.registration.userregistration;

import java2.businesslogic.ValidationError;
import java2.database.UserDatabase;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class AddUserValidator {
    @Autowired private UserDatabase userDatabase;

    public List<ValidationError> validate(String login, String password, String name, String email) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(login);
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyEmailError = validateEmail(email);
        emptyEmailError.ifPresent(error -> errors.add(error));
        validatePassword(password).ifPresent(error -> errors.add(error));
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

    private Optional<ValidationError> validatePassword(String password) {
        if(password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "Must not be empty!"));
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
