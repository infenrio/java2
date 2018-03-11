package java2.businesslogic;

import java.util.List;

public class ServiceResponse {
    private boolean success;
    private List<ValidationError> errors;

    public ServiceResponse(boolean success, List<ValidationError> errors) {
        this.success = success;
        this.errors = errors;
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
