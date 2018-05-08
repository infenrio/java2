package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="announcement_category")
public class AnnouncementCategory {

    @Id
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="parent_category_idref", nullable = true)
    private AnnouncementCategory parentCategory;

    @ManyToOne
    @JoinColumn(name = "title_term_idref", nullable = false)
    private Term title;

    @ManyToOne
    @JoinColumn(name = "description_term_idref", nullable = false)
    private Term description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnnouncementCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(AnnouncementCategory parentCategory) {
        this.parentCategory = parentCategory;
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

        AnnouncementCategory that = (AnnouncementCategory) o;

        if (id != that.id) return false;
        if (parentCategory != null ? !parentCategory.equals(that.parentCategory) : that.parentCategory != null)
            return false;
        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnnouncementCategory{" +
                "id=" + id +
                ", parentCategory=" + parentCategory +
                ", title=" + title +
                ", description=" + description +
                '}';
    }
}
