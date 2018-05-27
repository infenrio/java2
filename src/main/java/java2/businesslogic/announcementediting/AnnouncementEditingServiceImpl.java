package java2.businesslogic.announcementediting;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AnnouncementEditingServiceImpl implements AnnouncementEditingService {
    @Autowired AnnouncementEditingValidator validator;
    @Autowired AnnouncementRepository announcementRepository;

    @Override
    @Transactional
    public AnnouncementEditingResponse edit(AnnouncementEditingRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new AnnouncementEditingResponse(validationErrors);
        }

        announcementRepository.changeTitle(request.getId(), request.getTitle());
        announcementRepository.changeDescription(request.getId(), request.getDescription());
        return new AnnouncementEditingResponse(request.getId());
    }
}
