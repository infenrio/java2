package java2.views;

import java2.businesslogic.ServiceResponse;
import java2.businesslogic.adduser.AddUserService;
import java2.businesslogic.adduser.AddUserValidator;
import java2.database.UserDatabase;

import java.util.Scanner;

public class AddUserView implements View {
    private AddUserService addUserService;

    public AddUserView(UserDatabase userDatabase) {
        AddUserValidator addUserValidator = new AddUserValidator(userDatabase);
        this.addUserService = new AddUserService(userDatabase, addUserValidator);
    }


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
