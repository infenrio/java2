package java2.businesslogic.logout;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface LogoutValidator {
    List<ValidationError> validate(LogoutRequest request);
}
