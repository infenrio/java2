package java2.models;

public class Announcement {
    private int id;
    private User creator;
    private String title;
    private String description;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Announcement() {
        this.state = "ACTIVE";
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void ban() {
        state = "BANNED";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Announcement that = (Announcement) o;

        if (id != that.id) return false;
        if (!creator.equals(that.creator)) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + creator.hashCode();
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
                ", state='" + state + '\'' +
                '}';
    }
}
