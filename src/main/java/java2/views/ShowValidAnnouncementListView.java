package java2.views;

import java2.database.AnnouncementDatabase;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowValidAnnouncementListView implements View {
    @Autowired private AnnouncementRepository announcementRepository;

    @Override
    public void execute() {
        System.out.println("Valid announcements:");
        for (Announcement announcement : announcementRepository.getValidAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
