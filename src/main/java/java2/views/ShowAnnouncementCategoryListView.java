package java2.views;

import java2.database.AnnouncementCategoryRepository;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import java2.domain.AnnouncementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAnnouncementCategoryListView implements View {
    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;

    @Override
    public void execute() {
        System.out.println("Announcement categories:");
        for (AnnouncementCategory announcementCategory : announcementCategoryRepository.getAllCategories()) {
            System.out.println(announcementCategory);
        }
    }
}
