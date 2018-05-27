package java2.views;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowAnnouncementByTitleListView implements View {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter title:");
        String title = sc.nextLine();
        System.out.println("Registered announcements:");
        for (Announcement announcement : announcementRepository.findByTitle(title)) {
            System.out.println(announcement);
        }
    }
}
