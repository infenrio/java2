package java2.businesslogic.announcementediting;

import java2.businesslogic.AnnouncementServiceResponse;
import java2.businesslogic.ValidationError;

import java.util.List;

public class AnnouncementEditingResponse extends AnnouncementServiceResponse {
    public AnnouncementEditingResponse(int announcementId) {
        super(announcementId);
    }

    public AnnouncementEditingResponse(List<ValidationError> errors) {
        super(errors);
    }
}
