package java2.businesslogic.login;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginValidatorImpl implements LoginValidator {
    @Autowired private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(LoginRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyPasswordError = validatePassword(request.getPassword());
        emptyPasswordError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> invalidRoleError = validateRole(request.getRole());
        invalidRoleError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> userBannedError = validateUserBanned(request.getLogin());
        userBannedError.ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent() && !userBannedError.isPresent()) {
            if(!invalidRoleError.isPresent()) {
                validateLoginExists(request.getLogin(), request.getRole()).ifPresent(errors::add);
            }
            if(!emptyPasswordError.isPresent()) {
                validateLoginAndPassword(request.getLogin(), request.getPassword()).ifPresent(errors::add);
            }
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

    private Optional<ValidationError> validateRole(char role) {
        if(role != 'U' && role != 'A' && role != 'S') {
            return Optional.of(new ValidationError("role", "Has not valid value!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateLoginExists(String login, char role) {
        if(login != null && !login.isEmpty()) {
            Optional<User> userOpt = userRepository.findByLoginAndRole(login, role);
            if(!userOpt.isPresent()) {
                return Optional.of(new ValidationError("login", "User not found!"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateLoginAndPassword(String login, String password) {
        if(login != null && !login.isEmpty()) {
            Optional<User> userOpt = userRepository.findByLoginAndPassword(login, password);
            if(!userOpt.isPresent()) {
                return Optional.of(new ValidationError("password", "Incorrect login data!"));
            }
        }
        return Optional.empty();
    }

    public Optional<ValidationError> validateUserBanned(String login) {
        Optional<User> foundUser = userRepository.findByLogin(login);
        if(foundUser.isPresent()) {
            if(foundUser.get().getState().getId().equals("BANNED")) {
                return Optional.of(new ValidationError("login", "User is banned!"));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
