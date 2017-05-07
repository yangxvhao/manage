package model;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private String password;

    private String role;

    public User(Integer id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User() {
        super();
    }
}