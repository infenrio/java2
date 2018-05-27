package java2.businesslogic.announcementcreation;

import java2.businesslogic.AnnouncementServiceResponse;
import java2.businesslogic.ValidationError;

import java.util.List;

public class AnnouncementCreationResponse extends AnnouncementServiceResponse {
    public AnnouncementCreationResponse(int announcementId) {
        super(announcementId);
    }

    public AnnouncementCreationResponse(List<ValidationError> errors) {
        super(errors);
    }
}
