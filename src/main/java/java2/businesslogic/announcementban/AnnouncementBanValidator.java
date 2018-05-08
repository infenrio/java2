package java2.businesslogic.announcementban;

import java2.businesslogic.ValidationError;

import java.util.List;

public interface AnnouncementBanValidator {
    List<ValidationError> validate(AnnouncementBanRequest request);
}
