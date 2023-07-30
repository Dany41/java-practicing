package org.practicalunittesting;

public class User {

    private String password;
    private String name;
    private String surname;

    public User(String password) {
        this.password = password;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    String getPassword() {
        return this.password;
    };

    void setPassword(String password) {
        this.password = password;
    };

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
