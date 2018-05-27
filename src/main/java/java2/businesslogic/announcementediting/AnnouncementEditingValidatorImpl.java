package java2.businesslogic.announcementediting;

import java2.businesslogic.AnnouncementFieldValidator;
import java2.businesslogic.ValidationError;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementEditingValidatorImpl implements AnnouncementEditingValidator {
    @Autowired AnnouncementFieldValidator fieldValidator;

    @Override
    public List<ValidationError> validate(AnnouncementEditingRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        Optional<ValidationError> emptyIdError = fieldValidator.validateId(request.getId());
        emptyIdError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyTitleError = fieldValidator.validateTitle(request.getTitle());
        emptyTitleError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyDescriptionError = fieldValidator.validateDescription(request.getDescription());
        emptyDescriptionError.ifPresent(error -> errors.add(error));
        Optional<ValidationError> emptyLoginError = fieldValidator.validateLogin(request.getLogin());
        emptyLoginError.ifPresent(error -> errors.add(error));
        if(errors.size() == 0) {
            Optional<ValidationError> announcementPresenceError = fieldValidator.validateAnnouncementPresence(request.getId());
            announcementPresenceError.ifPresent(errors::add);
            if(!announcementPresenceError.isPresent()) {
                fieldValidator.validateAnnouncementAlreadyBanned(request.getId()).ifPresent(errors::add);
                fieldValidator.validateLoginOfCreator(request.getLogin(), request.getId()).ifPresent(errors::add);
            }
        }
        return errors;
    }

}
