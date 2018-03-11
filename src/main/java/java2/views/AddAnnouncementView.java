package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.addannouncement.AddAnnouncementService;
import java2.businesslogic.addannouncement.AddAnnouncementValidator;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;

import java.util.Scanner;

public class AddAnnouncementView implements View {
    private AddAnnouncementService addAnnouncementService;

    public AddAnnouncementView(AnnouncementDatabase announcementDatabase, UserDatabase userDatabase) {
        AddAnnouncementValidator addAnnouncementValidator = new AddAnnouncementValidator(announcementDatabase, userDatabase);
        this.addAnnouncementService = new AddAnnouncementService(announcementDatabase, userDatabase,
                addAnnouncementValidator);
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
        ServiceResponse response = addAnnouncementService.addAnnouncement(login, title, description);
        if(response.isSuccess()) {
            System.out.println("Announcement '" + title + "' from user '" + login + "' successfully registered!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
