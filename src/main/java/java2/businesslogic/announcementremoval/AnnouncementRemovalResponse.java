package java2.businesslogic.announcementremoval;

import java2.businesslogic.AnnouncementServiceResponse;
import java2.businesslogic.ValidationError;

import java.util.List;

public class AnnouncementRemovalResponse extends AnnouncementServiceResponse {
    public AnnouncementRemovalResponse(int announcementId) {
        super(announcementId);
    }

    public AnnouncementRemovalResponse(List<ValidationError> errors) {
        super(errors);
    }
}
