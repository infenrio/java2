package java2.domain.builders;

import java2.domain.AnnouncementCategory;
import java2.domain.Term;

public class AnnouncementCategoryBuilder {
    private int id;
    private AnnouncementCategory parentCategory;
    private Term title;
    private Term description;

    private AnnouncementCategoryBuilder() {}

    public static AnnouncementCategoryBuilder createAnnouncementCategory() {
        return new AnnouncementCategoryBuilder();
    }

    public AnnouncementCategory build() {
        AnnouncementCategory announcementCategory = new AnnouncementCategory();
        announcementCategory.setId(id);
        announcementCategory.setParentCategory(parentCategory);
        announcementCategory.setTitle(title);
        announcementCategory.setDescription(description);
        return announcementCategory;
    }

    public AnnouncementCategoryBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AnnouncementCategoryBuilder withParentCategory(AnnouncementCategory parentCategory) {
        this.parentCategory = parentCategory;
        return this;
    }

    public AnnouncementCategoryBuilder withParentCategory(AnnouncementCategoryBuilder parentCategory) {
        this.parentCategory = parentCategory.build();
        return this;
    }

    public AnnouncementCategoryBuilder withTitle(Term title) {
        this.title = title;
        return this;
    }

    public AnnouncementCategoryBuilder withTitle(TermBuilder title) {
        this.title = title.build();
        return this;
    }

    public AnnouncementCategoryBuilder withDescription(Term description) {
        this.description = description;
        return this;
    }

    public AnnouncementCategoryBuilder withDescription(TermBuilder description) {
        this.description = description.build();
        return this;
    }
}
