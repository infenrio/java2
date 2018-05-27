package java2.businesslogic.logout;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LogoutValidatorImpl implements LogoutValidator {
    @Autowired private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(LogoutRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyLoginError = validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> invalidRoleError = validateRole(request.getRole());
        invalidRoleError.ifPresent(error -> errors.add(error));
        if(!emptyLoginError.isPresent() && !invalidRoleError.isPresent()) {
            validateLoginExists(request.getLogin(), request.getRole()).ifPresent(errors::add);
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
}
