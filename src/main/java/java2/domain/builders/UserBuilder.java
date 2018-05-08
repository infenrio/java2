package java2.domain.builders;

import java2.domain.User;
import java2.domain.UserState;

public class UserBuilder {
    private int id;
    private String login;
    private String password;
    private char role;
    private String name;
    private String email;
    private UserState state;

    private UserBuilder() {}

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setName(name);
        user.setEmail(email);
        user.setState(state);
        return user;
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(char role) {
        this.role = role;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withState(UserState state) {
        this.state = state;
        return this;
    }

    public UserBuilder withState(UserStateBuilder state) {
        this.state = state.build();
        return this;
    }
}
