package java2.views;

import java2.database.AnnouncementDatabase;
import java2.models.Announcement;

public class ShowValidAnnouncementsView implements View {
    private AnnouncementDatabase announcementDatabase;

    public ShowValidAnnouncementsView(AnnouncementDatabase announcementDatabase) {
        this.announcementDatabase = announcementDatabase;
    }

    @Override
    public void execute() {
        System.out.println("Valid announcements:");
        for (Announcement announcement : announcementDatabase.getValidAnnouncements()) {
            System.out.println(announcement);
        }
    }
}
