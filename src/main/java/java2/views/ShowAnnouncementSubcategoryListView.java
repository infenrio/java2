package java2.views;

import java2.database.AnnouncementCategoryRepository;
import java2.domain.AnnouncementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowAnnouncementSubcategoryListView implements View {
    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category id:");
        int categoryId = sc.nextInt();
        System.out.println("Announcement subcategories:");
        for (AnnouncementCategory announcementCategory : announcementCategoryRepository.getCategoriesByParentCategoryId(categoryId)) {
            System.out.println(announcementCategory);
        }
    }
}
