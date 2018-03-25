package java2.views;

import java2.database.AnnouncementDatabase;
import java2.models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowValidAnnouncementsView implements View {
    @Autowired private AnnouncementDatabase announcementDatabase;

    @Override
    public void execute() {
        System.out.println("Valid announcements:");
        for (Announcement announcement : announcementDatabase.getValidAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
