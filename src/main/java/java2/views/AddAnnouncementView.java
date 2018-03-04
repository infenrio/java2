package java2.views;

import java2.businesslogic.AddAnnouncementService;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;

import java.util.Scanner;

public class AddAnnouncementView implements View {
    private AddAnnouncementService addAnnouncementService;

    public AddAnnouncementView(AnnouncementDatabase announcementDatabase, UserDatabase userDatabase) {
        this.addAnnouncementService = new AddAnnouncementService(announcementDatabase, userDatabase);
    }

    @Override
    public void execute() {
        System.out.println("Register new announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter title:");
        String title = sc.nextLine();
        System.out.print("Enter description:");
        String description = sc.nextLine();
        boolean result = addAnnouncementService.addAnnouncement(login, title, description);
        if(result) {
            System.out.println("Announcement '" + title + "' from user '" + login + "' successfully registered!");
        } else {
            System.out.println("User '" + login + "' does not exist, announcement was not created!");
        }
    }
}
