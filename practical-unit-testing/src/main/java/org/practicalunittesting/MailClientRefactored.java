package org.practicalunittesting;

public class MailClientRefactored {

    private EmailServerRefactored emailServer;

    public MailClientRefactored(EmailServerRefactored emailServer) {
        this.emailServer = emailServer;
    }


    public void sendEmail(String address, String title, String body) {
        Email email = Email.of(address, title, body);
        emailServer.sendEmail(email);
    }
}
