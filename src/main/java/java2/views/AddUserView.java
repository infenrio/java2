package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.adduser.AddUserService;
import java2.businesslogic.adduser.AddUserValidator;
import java2.database.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserView implements View {
    @Autowired private AddUserService addUserService;

    @Override
    public void execute() {
        System.out.println("Register new user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter name:");
        String name = sc.nextLine();
        System.out.print("Enter email:");
        String email = sc.nextLine();
        ServiceResponse response = addUserService.addUser(login, name, email);
        if(response.isSuccess()) {
            System.out.println("User '" + login + "' successfully registered!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
