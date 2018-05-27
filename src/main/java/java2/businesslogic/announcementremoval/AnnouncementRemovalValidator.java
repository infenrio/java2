package java2.businesslogic.announcementremoval;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface AnnouncementRemovalValidator {
    List<ValidationError> validate(AnnouncementRemovalRequest request);
}
