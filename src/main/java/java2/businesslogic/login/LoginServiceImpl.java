package java2.businesslogic.login;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired private LoginValidator validator;
    @Autowired private UserRepository userRepository;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new LoginResponse(validationErrors);
        }
        Optional<User> userFound = userRepository.findByLogin(request.getLogin());
        if(userFound.isPresent()) {
            User user = userFound.get();
            return new LoginResponse(user.getId(), user.getRole());
        } else {
            return new LoginResponse(null);
        }
    }
}
