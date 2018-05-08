package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface AnnouncementCreationValidator {
    List<ValidationError> validate(AnnouncementCreationRequest request);
}
