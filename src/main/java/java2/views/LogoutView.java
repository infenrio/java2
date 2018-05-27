package java2.views;

import java2.businesslogic.logout.LogoutRequest;
import java2.businesslogic.logout.LogoutResponse;
import java2.businesslogic.logout.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LogoutView implements View {
    @Autowired private LogoutService logoutService;

    @Override
    public void execute() {
        System.out.println("Logout with user!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter login:");
        String login = sc.nextLine();
        LogoutRequest request = new LogoutRequest(login, 'U');
        LogoutResponse response = logoutService.logout(request);
        if(response.isSuccess()) {
            System.out.println("User '" + login + "' successfully logged out!");
        } else {
            response.getErrors().forEach(error -> {
                System.out.println("ValidationError field = " + error.getField());
                System.out.println("ValidationError message = " + error.getErrorMessage());
            });
        }
    }
}
