package java2.businesslogic.logout;

public class LogoutRequest {
    private String login;
    private char role;

    public LogoutRequest(String login, char role) {
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }
}
