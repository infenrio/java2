package java2.businesslogic.announcementremoval;

import java2.businesslogic.ValidationError;
import java2.businesslogic.announcementcreation.AnnouncementCreationResponse;
import java2.businesslogic.announcementediting.AnnouncementEditingValidator;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementRemovalServiceImpl implements AnnouncementRemovalService {
    @Autowired AnnouncementRemovalValidator validator;
    @Autowired AnnouncementRepository announcementRepository;

    @Override
    @Transactional
    public AnnouncementRemovalResponse remove(AnnouncementRemovalRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new AnnouncementRemovalResponse(validationErrors);
        }
        Optional<Announcement> announcement = announcementRepository.findById(request.getId());
        if(announcement.isPresent()) {
            announcementRepository.remove(announcement.get());
            return new AnnouncementRemovalResponse(announcement.get().getId());
        } else {
            return new AnnouncementRemovalResponse(null);
        }
    }
}
