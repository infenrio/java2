package java2.businesslogic.userban;

import java2.businesslogic.ValidationError;

import java.util.List;

public class UserBanResponse {
    private int userId;
    private boolean success;
    private List<ValidationError> errors;

    public UserBanResponse(int userId) {
        this.userId = userId;
        this.success = true;
        this.errors = null;
    }

    public UserBanResponse(List<ValidationError> errors) {
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
