package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.banuser.BanUserService;
import java2.businesslogic.banuser.BanUserValidator;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BanUserView implements View {
    @Autowired private BanUserService banUserService;

    @Override
    public void execute() {
        System.out.println("Ban existing user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        ServiceResponse response = banUserService.banUser(login);
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
