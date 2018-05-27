package java2.businesslogic.login;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface LoginValidator {
    List<ValidationError> validate(LoginRequest request);
}
