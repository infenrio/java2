package java2.businesslogic.announcementban;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AnnouncementBanServiceImpl implements AnnouncementBanService {
    @Autowired private AnnouncementBanValidator validator;
    @Autowired private AnnouncementRepository announcementRepository;

    @Override
    @Transactional
    public AnnouncementBanResponse ban(AnnouncementBanRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new AnnouncementBanResponse(validationErrors);
        }

        announcementRepository.banById(request.getId());
        return new AnnouncementBanResponse(request.getId());
    }
}
