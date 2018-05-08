package java2.businesslogic.announcementcreation;

public class AnnouncementCreationRequest {
    private int categoryId;
    private String title;
    private String description;
    private String login;

    public AnnouncementCreationRequest(int categoryId, String title, String description, String login) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.login = login;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
