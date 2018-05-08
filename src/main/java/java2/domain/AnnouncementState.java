package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="announcement_state")
public class AnnouncementState {

    @Id
    @Column(name="id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "title_term_idref", nullable = false)
    private Term title;

    @ManyToOne
    @JoinColumn(name = "description_term_idref", nullable = false)
    private Term description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Term getTitle() {
        return title;
    }

    public void setTitle(Term title) {
        this.title = title;
    }

    public Term getDescription() {
        return description;
    }

    public void setDescription(Term description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnouncementState that = (AnnouncementState) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnnouncementState{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", description=" + description +
                '}';
    }
}
