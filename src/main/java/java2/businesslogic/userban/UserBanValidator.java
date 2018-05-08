package java2.businesslogic.userban;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface UserBanValidator {
    List<ValidationError> validate(UserBanRequest request);
}
