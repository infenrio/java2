package java2.models;

public class Announcement {
    private User creator;
    private String title;
    private String description;
    private AnnouncementState state;

    public Announcement() {
        this.state = AnnouncementState.ACTIVE;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public AnnouncementState getState() {
        return state;
    }

    public void setState(AnnouncementState state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void ban() {
        state = AnnouncementState.BANNED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Announcement that = (Announcement) o;

        if (!creator.equals(that.creator)) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        return state == that.state;
    }

    @Override
    public int hashCode() {
        int result = creator.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "creator=" + creator +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state.getStateText() + '\'' +
                '}';
    }
}
