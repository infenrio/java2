package java2;

import java2.database.*;
import java2.views.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserRealDatabase();
        AnnouncementDatabase announcementDatabase = new AnnouncementRealDatabase(userDatabase);

        View addUserView = new AddUserView(userDatabase);
        View addAnnouncementView = new AddAnnouncementView(announcementDatabase, userDatabase);
        View showUserListView = new ShowUserListView(userDatabase);
        View showAnnouncementListView = new ShowAnnouncementListView(announcementDatabase);
        View banAnnouncementView = new BanAnnouncementView(announcementDatabase);
        View banUserView = new BanUserView(userDatabase);
        View showValidAnnouncementsView = new ShowValidAnnouncementsView(announcementDatabase);
        View programExitView = new ProgramExitView();

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, addUserView);
        actionMap.put(2, addAnnouncementView);
        actionMap.put(3, showUserListView);
        actionMap.put(4, showAnnouncementListView);
        actionMap.put(5, banAnnouncementView);
        actionMap.put(6, banUserView);
        actionMap.put(7, showValidAnnouncementsView);
        actionMap.put(8, programExitView);

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
