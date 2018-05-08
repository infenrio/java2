package java2.businesslogic.userregistration;

import java2.businesslogic.ValidationError;

import java.util.List;

public class UserRegistrationResponse {
    private int userId;
    private boolean success;
    private List<ValidationError> errors;

    public UserRegistrationResponse(int userId) {
        this.userId = userId;
        this.success = true;
        this.errors = null;
    }

    public UserRegistrationResponse(List<ValidationError> errors) {
        this.userId = 0;
        this.success = false;
        this.errors = errors;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
