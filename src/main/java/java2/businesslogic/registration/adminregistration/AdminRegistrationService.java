package java2.businesslogic.registration.adminregistration;

import java2.businesslogic.registration.RegistrationRequest;
import java2.businesslogic.registration.RegistrationResponse;

public interface AdminRegistrationService {
    RegistrationResponse register(RegistrationRequest request);
}
