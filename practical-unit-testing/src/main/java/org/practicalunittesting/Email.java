package org.practicalunittesting;

public class Email {
    public static Email of(String address, String title, String body) {
        return new Email(address, title, body);
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    private final String address;
    private final String title;
    private final String body;

    public Email(String address, String title, String body) {
        this.address = address;
        this.title = title;
        this.body = body;
    }
}
