package java2.views;

import java2.models.Announcement;
import java2.database.AnnouncementDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAnnouncementListView implements View {
    @Autowired private AnnouncementDatabase announcementDatabase;

    @Override
    public void execute() {
        System.out.println("Registered announcements:");
        for (Announcement announcement : announcementDatabase.getAllAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
