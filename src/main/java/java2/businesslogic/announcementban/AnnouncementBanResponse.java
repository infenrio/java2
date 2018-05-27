package java2.businesslogic.announcementban;

import java2.businesslogic.ValidationError;

import java.util.List;

public class AnnouncementBanResponse {
    private int announcementId;
    private boolean success;
    private List<ValidationError> errors;

    public AnnouncementBanResponse(int announcementId) {
        this.announcementId = announcementId;
        this.success = true;
        this.errors = null;
    }

    public AnnouncementBanResponse(List<ValidationError> errors) {
        this.announcementId = 0;
        this.success = false;
        this.errors = errors;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
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
