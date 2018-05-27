package java2.businesslogic.registration;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RegistrationValidatorImpl implements RegistrationValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(RegistrationRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyEmailError = validateEmail(request.getEmail());
        emptyEmailError.ifPresent(error -> errors.add(error));
        validatePassword(request.getPassword()).ifPresent(error -> errors.add(error));
        validateName(request.getName()).ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent()) {
            validateDuplicateLogin(request.getLogin()).ifPresent(errors::add);
        }
        if(!emptyEmailError.isPresent()) {
            validateDuplicateEmail(request.getEmail()).ifPresent(errors::add);
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
            Optional<User> userOpt = userRepository.findByLogin(login);
            if(userOpt.isPresent()) {
                return Optional.of(new ValidationError("login", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateDuplicateEmail(String email) {
        if(email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if(userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated!"));
            }
        }
        return Optional.empty();
    }
}
