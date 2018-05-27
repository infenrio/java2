package java2.businesslogic.registration;

import java2.businesslogic.ValidationError;
import java2.businesslogic.registration.RegistrationRequest;

import java.util.List;

public interface RegistrationValidator {
    List<ValidationError> validate(RegistrationRequest request);
}
