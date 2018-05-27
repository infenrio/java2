package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.announcementban.AnnouncementBanRequest;
import java2.businesslogic.announcementban.AnnouncementBanResponse;
import java2.businesslogic.announcementban.AnnouncementBanService;
import java2.businesslogic.announcementban.BanAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BanAnnouncementView implements View {
    @Autowired private AnnouncementBanService announcementBanService;

    @Override
    public void execute() {
        System.out.println("Ban existing announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter id:");
        int id = sc.nextInt();
        AnnouncementBanRequest request = new AnnouncementBanRequest(login, id);
        AnnouncementBanResponse response = announcementBanService.ban(request);
        if(response.isSuccess()) {
            System.out.println("Announcement successfully banned!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
