package java2.views;

import java2.businesslogic.announcementremoval.AnnouncementRemovalRequest;
import java2.businesslogic.announcementremoval.AnnouncementRemovalResponse;
import java2.businesslogic.announcementremoval.AnnouncementRemovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveAnnouncementView implements View {
    @Autowired AnnouncementRemovalService service;

    @Override
    public void execute() {
        System.out.println("Remove announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter login:");
        String login = sc.nextLine();
        AnnouncementRemovalRequest request = new AnnouncementRemovalRequest(login, id);
        AnnouncementRemovalResponse response = service.remove(request);
        if(response.isSuccess()) {
            System.out.println("Announcement from user '" + login + "' successfully deleted!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
