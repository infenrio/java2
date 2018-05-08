package java2.businesslogic.userregistration;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface UserRegistrationValidator {
    List<ValidationError> validate(UserRegistrationRequest request);
}
