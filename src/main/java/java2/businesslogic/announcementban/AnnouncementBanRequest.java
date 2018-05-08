package java2.businesslogic.announcementban;

public class AnnouncementBanRequest {
    private String login;
    private String title;

    public AnnouncementBanRequest(String login, String title) {
        this.login = login;
        this.title = title;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
