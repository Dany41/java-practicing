package org.practicalunittesting;

public class User {

    String password;

    public User(String password) {
        this.password = password;
    }

    String getPassword() {
        return this.password;
    };

    void setPassword(String password) {
        this.password = password;
    };
}
