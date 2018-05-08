package java2.domain.builders;

import java2.domain.*;

public class AnnouncementBuilder {
    private int id;
    private AnnouncementCategory category;
    private String title;
    private String description;
    private User creator;
    private AnnouncementState state;

    private AnnouncementBuilder() {}

    public static AnnouncementBuilder createAnnouncement() {
        return new AnnouncementBuilder();
    }

    public Announcement build() {
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement.setCategory(category);
        announcement.setTitle(title);
        announcement.setDescription(description);
        announcement.setCreator(creator);
        announcement.setState(state);
        return announcement;
    }

    public AnnouncementBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AnnouncementBuilder withCategory(AnnouncementCategory category) {
        this.category = category;
        return this;
    }

    public AnnouncementBuilder withCategory(AnnouncementCategoryBuilder category) {
        this.category = category.build();
        return this;
    }

    public AnnouncementBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public AnnouncementBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public AnnouncementBuilder withCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public AnnouncementBuilder withCreator(UserBuilder creator) {
        this.creator = creator.build();
        return this;
    }

    public AnnouncementBuilder withState(AnnouncementState state) {
        this.state = state;
        return this;
    }

    public AnnouncementBuilder withState(AnnouncementStateBuilder state) {
        this.state = state.build();
        return this;
    }
}
