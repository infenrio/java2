package java2.views;

import java2.businesslogic.login.LoginRequest;
import java2.businesslogic.login.LoginResponse;
import java2.businesslogic.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginView implements View {
    @Autowired private LoginService loginService;

    @Override
    public void execute() {
        System.out.println("Login with user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        LoginRequest request = new LoginRequest(login, password, 'U');
        LoginResponse response = loginService.login(request);
        if(response.isSuccess()) {
            System.out.println("User '" + login + "' successfully logged in!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
