package java2.businesslogic.userban;

import java2.businesslogic.ValidationError;
import java2.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserBanServiceImpl implements UserBanService {
    @Autowired private UserBanValidator validator;
    @Autowired private UserRepository userRepository;

    @Override
    @Transactional
    public UserBanResponse ban(UserBanRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserBanResponse(validationErrors);
        }

        userRepository.banByLogin(request.getLogin());
        return new UserBanResponse(userRepository.findByLogin(request.getLogin()).get().getId());
    }
}
