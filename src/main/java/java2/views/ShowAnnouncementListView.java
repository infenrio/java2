package java2.views;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import java2.database.AnnouncementDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAnnouncementListView implements View {
    @Autowired private AnnouncementRepository announcementRepository;

    @Override
    public void execute() {
        System.out.println("Registered announcements:");
        for (Announcement announcement : announcementRepository.getAllAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
