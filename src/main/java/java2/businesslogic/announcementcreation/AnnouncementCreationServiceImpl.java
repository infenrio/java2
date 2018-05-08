package java2.businesslogic.announcementcreation;

import java2.businesslogic.ValidationError;
import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.database.AnnouncementStateRepository;
import java2.database.UserRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import java2.domain.AnnouncementState;
import java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java2.domain.builders.AnnouncementBuilder.createAnnouncement;

@Component
public class AnnouncementCreationServiceImpl implements AnnouncementCreationService {
    @Autowired private AnnouncementRepository announcementRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AnnouncementCreationValidator validator;
    @Autowired private AnnouncementStateRepository announcementStateRepository;
    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;

    @Override
    @Transactional
    public AnnouncementCreationResponse create(AnnouncementCreationRequest request) {
        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new AnnouncementCreationResponse(validationErrors);
        }

        AnnouncementState announcementState = announcementStateRepository.findById("ACTIVE").get();
        User user = userRepository.findByLogin(request.getLogin()).get();
        AnnouncementCategory announcementCategory = announcementCategoryRepository.findById(request.getCategoryId()).get();

        Announcement announcement = createAnnouncement()
                .withCategory(announcementCategory)
                .withTitle(request.getTitle())
                .withDescription(request.getDescription())
                .withCreator(user)
                .withState(announcementState)
                .build();

        announcementRepository.save(announcement);

        return new AnnouncementCreationResponse(announcement.getId());
    }
}
