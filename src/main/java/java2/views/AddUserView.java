package java2.views;

import java2.businesslogic.AddUserService;
import java2.database.UserDatabase;

import java.util.Scanner;

public class AddUserView implements View {
    private AddUserService addUserService;

    public AddUserView(UserDatabase userDatabase) {
        this.addUserService = new AddUserService(userDatabase);
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

        boolean result = addUserService.addUser(login, name, email);
        if(result) {
            System.out.println("User '" + login + "' sucessfully registered!");
        } else {
            System.out.println("User '" + login + "' already exists!");
        }
    }
}
