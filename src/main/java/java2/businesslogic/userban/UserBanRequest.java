package java2.businesslogic.userban;

public class UserBanRequest {
    private String login;

    public UserBanRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
