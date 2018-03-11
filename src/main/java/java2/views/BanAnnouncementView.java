package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.banannouncement.BanAnnouncementService;
import java2.businesslogic.banannouncement.BanAnnouncementValidator;
import java2.database.AnnouncementDatabase;

import java.util.Scanner;

public class BanAnnouncementView implements View {
    private BanAnnouncementService banAnnouncementService;

    public BanAnnouncementView(AnnouncementDatabase announcementDatabase) {
        BanAnnouncementValidator banAnnouncementValidator = new BanAnnouncementValidator(announcementDatabase);
        banAnnouncementService = new BanAnnouncementService(announcementDatabase, banAnnouncementValidator);
    }

    @Override
    public void execute() {
        System.out.println("Ban existing announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter title:");
        String title = sc.nextLine();
        ServiceResponse response = banAnnouncementService.banAnnouncement(login, title);
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
