package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.addannouncement.AddAnnouncementService;
import java2.businesslogic.addannouncement.AddAnnouncementValidator;
import java2.database.AnnouncementDatabase;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAnnouncementView implements View {
    @Autowired private AddAnnouncementService addAnnouncementService;

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
