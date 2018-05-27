package java2.businesslogic.registration.userregistration;

import java2.businesslogic.ValidationError;
import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;
import java2.businesslogic.registration.RegistrationValidator;
import java2.database.UserRepository;
import java2.database.UserStateRepository;
import java2.domain.User;
import java2.domain.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java2.domain.builders.UserBuilder.createUser;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired private RegistrationValidator validator;
    @Autowired private UserRepository userRepository;
    @Autowired private UserStateRepository userStateRepository;

    @Override
    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {
        // validate user data
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new RegistrationResponse(validationErrors);
        }

        // prepare active state for user
        UserState userState = userStateRepository.findById("ACTIVE").get();

        // create new user
        User user = createUser()
                .withLogin(request.getLogin())
                .withPassword(request.getPassword())
                .withRole('U')
                .withName(request.getName())
                .withEmail(request.getEmail())
                .withState(userState)
                .build();

        // store to db
        userRepository.save(user);

        return new RegistrationResponse(user.getId());
    }
}
