package java2;

import java2.configs.SpringAppConfig;
import java2.views.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, applicationContext.getBean(AddUserView.class));
        actionMap.put(2, applicationContext.getBean(AddAnnouncementView.class));
        actionMap.put(3, applicationContext.getBean(ShowUserListView.class));
        actionMap.put(4, applicationContext.getBean(ShowAnnouncementListView.class));
        actionMap.put(5, applicationContext.getBean(BanAnnouncementView.class));
        actionMap.put(6, applicationContext.getBean(BanUserView.class));
        actionMap.put(7, applicationContext.getBean(ShowValidAnnouncementListView.class));
        actionMap.put(8, applicationContext.getBean(ShowAnnouncementCategoryView.class));
        actionMap.put(9, applicationContext.getBean(ShowAnnouncementCategoryListView.class));
        actionMap.put(10, applicationContext.getBean(ShowAnnouncementSubcategoryListView.class));
        actionMap.put(11, applicationContext.getBean(ShowAnnouncementByTitleListView.class));
        actionMap.put(12, applicationContext.getBean(ShowAnnouncementByDescriptionListView.class));
        actionMap.put(13, applicationContext.getBean(EditAnnouncementView.class));
        actionMap.put(14, applicationContext.getBean(RemoveAnnouncementView.class));
        actionMap.put(15, applicationContext.getBean(AddAdminView.class));
        actionMap.put(16, applicationContext.getBean(LoginView.class));
        actionMap.put(17, applicationContext.getBean(LogoutView.class));
        actionMap.put(18, applicationContext.getBean(ProgramExitView.class));

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            View view = actionMap.get(menuItem);
            view.execute();
        }
    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. Add user.");
        System.out.println("2. Add announcement.");
        System.out.println("3. Show all users.");
        System.out.println("4. Show all announcements.");
        System.out.println("5. Ban announcement.");
        System.out.println("6. Ban user.");
        System.out.println("7. Show only valid announcements.");
        System.out.println("8. Show all announcements of specific category.");
        System.out.println("9. Show announcement categories.");
        System.out.println("10. Show announcement subcategories.");
        System.out.println("11. Show announcements by title.");
        System.out.println("12. Show announcements by description.");
        System.out.println("13. Edit announcement.");
        System.out.println("14. Remove announcement.");
        System.out.println("15. Add admin.");
        System.out.println("16. Login.");
        System.out.println("17. Logout.");
        System.out.println("18. Exit program.");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
