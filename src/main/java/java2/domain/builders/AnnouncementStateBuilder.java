package java2.domain.builders;

import java2.domain.AnnouncementState;
import java2.domain.Term;

public class AnnouncementStateBuilder {
    private String id;
    private Term title;
    private Term description;

    private AnnouncementStateBuilder() {}

    public static AnnouncementStateBuilder createAnnouncementState() {
        return new AnnouncementStateBuilder();
    }

    public AnnouncementState build() {
        AnnouncementState announcementState = new AnnouncementState();
        announcementState.setId(id);
        announcementState.setTitle(title);
        announcementState.setDescription(description);
        return announcementState;
    }

    public AnnouncementStateBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public AnnouncementStateBuilder withTitle(Term title) {
        this.title = title;
        return this;
    }

    public AnnouncementStateBuilder withTitle(TermBuilder title) {
        this.title = title.build();
        return this;
    }

    public AnnouncementStateBuilder withDescription(Term description) {
        this.description = description;
        return this;
    }

    public AnnouncementStateBuilder withDescription(TermBuilder description) {
        this.description = description.build();
        return this;
    }
}
