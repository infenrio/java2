package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="login", nullable = false)
    private String login;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="role", nullable = false)
    private char role;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name="state_idref", nullable = false)
    private UserState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != user.role) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!name.equals(user.name)) return false;
        if (!email.equals(user.email)) return false;
        return state.equals(user.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (int) role;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                '}';
    }

    //    public User() {
//        this.state = "ACTIVE";
//    }



//    public void ban() {
//        state = "BANNED";
//    }

}
