package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.userban.BanUserService;
import java2.businesslogic.userban.UserBanRequest;
import java2.businesslogic.userban.UserBanResponse;
import java2.businesslogic.userban.UserBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BanUserView implements View {
    @Autowired private UserBanService userBanService;

    @Override
    public void execute() {
        System.out.println("Ban existing user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        UserBanRequest request = new UserBanRequest(login);
        UserBanResponse response = userBanService.ban(request);
        if(response.isSuccess()) {
            System.out.println("User '" + login + "' successfully banned!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
