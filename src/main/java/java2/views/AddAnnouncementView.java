package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.announcementcreation.AddAnnouncementService;
import java2.businesslogic.announcementcreation.AnnouncementCreationRequest;
import java2.businesslogic.announcementcreation.AnnouncementCreationResponse;
import java2.businesslogic.announcementcreation.AnnouncementCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAnnouncementView implements View {
    @Autowired private AnnouncementCreationService announcementCreationService;

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
        AnnouncementCreationRequest request = new AnnouncementCreationRequest(1000, title, description, login);
        AnnouncementCreationResponse response = announcementCreationService.create(request);
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
