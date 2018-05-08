package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="user_state")
public class UserState {

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

        UserState userState = (UserState) o;

        if (!id.equals(userState.id)) return false;
        if (!title.equals(userState.title)) return false;
        return description.equals(userState.description);
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
        return "UserState{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", description=" + description +
                '}';
    }
}
