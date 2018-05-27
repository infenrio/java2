package java2.businesslogic.announcementediting;

public class AnnouncementEditingRequest {
    private int id;
    private String title;
    private String description;
    private String login;

    public AnnouncementEditingRequest(int id, String title, String description, String login) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
