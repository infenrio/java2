package java2.views;

import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowAnnouncementCategoryView implements View {
    @Autowired private AnnouncementRepository announcementRepository;

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category id:");
        int categoryId = sc.nextInt();
        System.out.println("Announcements of category:");
        for (Announcement announcement : announcementRepository.findByCategory(categoryId)) {
            System.out.println(announcement);
        }
    }
}
