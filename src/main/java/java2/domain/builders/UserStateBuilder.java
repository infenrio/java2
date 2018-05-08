package java2.domain.builders;

import java2.domain.Term;
import java2.domain.UserState;

public class UserStateBuilder {
    private String id;
    private Term title;
    private Term description;

    private UserStateBuilder() {}

    public static UserStateBuilder createUserState() {
        return new UserStateBuilder();
    }

    public UserState build() {
        UserState userState = new UserState();
        userState.setId(id);
        userState.setTitle(title);
        userState.setDescription(description);
        return userState;
    }

    public UserStateBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserStateBuilder withTitle(Term title) {
        this.title = title;
        return this;
    }

    public UserStateBuilder withTitle(TermBuilder title) {
        this.title = title.build();
        return this;
    }

    public UserStateBuilder withDescription(Term description) {
        this.description = description;
        return this;
    }

    public UserStateBuilder withDescription(TermBuilder description) {
        this.description = description.build();
        return this;
    }
}
