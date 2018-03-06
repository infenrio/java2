package java2.views;

import java2.businesslogic.BanUserService;
import java2.database.UserDatabase;

import java.util.Scanner;

public class BanUserView implements View {
    private BanUserService banUserService;

    public BanUserView(UserDatabase userDatabase) {
        banUserService = new BanUserService(userDatabase);
    }

    @Override
    public void execute() {
        System.out.println("Ban existing user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        String result = banUserService.banUser(login);
        System.out.println(result);
    }
}
