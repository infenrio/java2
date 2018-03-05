package java2.views;

import java2.models.Announcement;
import java2.database.AnnouncementDatabase;

public class ShowAnnouncementListView implements View {
    private AnnouncementDatabase announcementDatabase;

    public ShowAnnouncementListView(AnnouncementDatabase announcementDatabase) {
        this.announcementDatabase = announcementDatabase;
    }

    @Override
    public void execute() {
        System.out.println("Registered announcements:");
        for (Announcement announcement : announcementDatabase.getAllAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
