package java2.businesslogic.registration.userregistration;

import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;

public interface UserRegistrationService {
    RegistrationResponse register(RegistrationRequest request);
}
