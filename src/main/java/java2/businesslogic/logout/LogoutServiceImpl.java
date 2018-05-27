package java2.businesslogic.logout;

import java2.businesslogic.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LogoutServiceImpl implements LogoutService {
    @Autowired private LogoutValidator validator;

    @Override
    @Transactional
    public LogoutResponse logout(LogoutRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new LogoutResponse(validationErrors);
        } else {
            return new LogoutResponse(request.getRole());
        }
    }
}
