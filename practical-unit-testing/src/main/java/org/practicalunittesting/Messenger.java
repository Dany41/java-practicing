package org.practicalunittesting;

public class Messenger {
    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }
    public void sendMessage(Client_MailServerExample client, Template template) {
        String msgContent =
                templateEngine.prepareMessage(template, client);
        mailServer.send(client.getEmail(), msgContent);
    }
}

