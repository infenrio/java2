package java2.businesslogic.announcementediting;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface AnnouncementEditingValidator {
    List<ValidationError> validate(AnnouncementEditingRequest request);
}
