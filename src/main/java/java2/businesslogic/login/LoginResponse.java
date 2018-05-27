package java2.businesslogic.login;

import java2.businesslogic.ValidationError;

import java.util.List;

public class LoginResponse {
    private int userId;
    private char role;
    private boolean success;
    private List<ValidationError> errors;

    public LoginResponse(int userId, char role) {
        this.userId = userId;
        this.role = role;
        this.success = true;
        this.errors = null;
    }

    public LoginResponse(List<ValidationError> errors) {
        this.userId = 0;
        this.role = 'U';
        this.success = false;
        this.errors = errors;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
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
