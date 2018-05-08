package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.userregistration.AddUserService;
import java2.businesslogic.userregistration.UserRegistrationRequest;
import java2.businesslogic.userregistration.UserRegistrationResponse;
import java2.businesslogic.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserView implements View {
    @Autowired private UserRegistrationService userRegistrationService;

    @Override
    public void execute() {
        System.out.println("Register new user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        System.out.print("Enter name:");
        String name = sc.nextLine();
        System.out.print("Enter email:");
        String email = sc.nextLine();
        UserRegistrationRequest request = new UserRegistrationRequest(login, password, name, email);
        UserRegistrationResponse response = userRegistrationService.register(request);
        if(response.isSuccess()) {
            System.out.println("User '" + login + "' successfully registered! Id = " + response.getUserId());
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
