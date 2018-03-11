package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.banuser.BanUserService;
import java2.businesslogic.banuser.BanUserValidator;
import java2.database.UserDatabase;

import java.util.Scanner;

public class BanUserView implements View {
    private BanUserService banUserService;

    public BanUserView(UserDatabase userDatabase) {
        BanUserValidator banUserValidator = new BanUserValidator(userDatabase);
        banUserService = new BanUserService(userDatabase, banUserValidator);
    }

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
