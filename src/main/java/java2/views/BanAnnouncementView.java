package java2.views;

import java2.businesslogic.BanAnnouncementService;
import java2.database.AnnouncementDatabase;

import java.util.Scanner;

public class BanAnnouncementView implements View {
    private BanAnnouncementService banAnnouncementService;

    public BanAnnouncementView(AnnouncementDatabase announcementDatabase) {
        banAnnouncementService = new BanAnnouncementService(announcementDatabase);
    }

    @Override
    public void execute() {
        System.out.println("Ban existing announcement!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter title:");
        String title = sc.nextLine();
        String result = banAnnouncementService.banAnnouncement(login, title);
        System.out.println(result);
    }
}
