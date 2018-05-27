package java2.businesslogic.logout;

import java2.businesslogic.ValidationError;

import java.util.List;

public class LogoutResponse {
    private char role;
    private boolean success;
    private List<ValidationError> errors;

    public LogoutResponse(char role) {
        this.role = role;
        this.success = true;
        this.errors = null;
    }

    public LogoutResponse(List<ValidationError> errors) {
        this.role = 'U';
        this.success = false;
        this.errors = errors;
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
