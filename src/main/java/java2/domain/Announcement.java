package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="announcements")
public class Announcement {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="category_idref", nullable = false)
    private AnnouncementCategory category;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="user_idref", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name="state_idref", nullable = false)
    private AnnouncementState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnnouncementCategory getCategory() {
        return category;
    }

    public void setCategory(AnnouncementCategory category) {
        this.category = category;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public AnnouncementState getState() {
        return state;
    }

    public void setState(AnnouncementState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Announcement that = (Announcement) o;

        if (id != that.id) return false;
        if (!category.equals(that.category)) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        if (!creator.equals(that.creator)) return false;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + category.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + creator.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", state=" + state +
                '}';
    }

    //    public Announcement() {
//        this.state = "ACTIVE";
//    }
//
//    public void ban() {
//        state = "BANNED";
//    }
}
