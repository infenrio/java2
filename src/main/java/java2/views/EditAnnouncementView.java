package java2.views;

import java2.businesslogic.announcementediting.AnnouncementEditingRequest;
import java2.businesslogic.announcementediting.AnnouncementEditingResponse;
import java2.businesslogic.announcementediting.AnnouncementEditingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EditAnnouncementView implements View {
    @Autowired private AnnouncementEditingService announcementEditingService;

    @Override
    public void execute() {
        System.out.println("Edit announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter title:");
        String title = sc.nextLine();
        System.out.print("Enter description:");
        String description = sc.nextLine();
        AnnouncementEditingRequest request = new AnnouncementEditingRequest(id, title, description, login);
        AnnouncementEditingResponse response = announcementEditingService.edit(request);
        if(response.isSuccess()) {
            System.out.println("Announcement '" + title + "' from user '" + login + "' successfully edited!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
