package java2;

import java2.configs.SpringAppConfig;
import java2.database.*;
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
        actionMap.put(7, applicationContext.getBean(ShowValidAnnouncementsView.class));
        actionMap.put(8, applicationContext.getBean(ProgramExitView.class));

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
        System.out.println("8. Exit program.");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
