package entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String fullname;

    public User(){

    }

    public User(int id, String name, String password, String role,String fullname) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.role = role;
        this.fullname=fullname;
    }
    public User(String name, String password, String role,String fullname) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.role = role;
        this.fullname=fullname;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
