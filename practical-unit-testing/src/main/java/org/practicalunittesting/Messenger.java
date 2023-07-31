package org.practicalunittesting;

public class Messenger {
    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger() {
    }

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

    public void sendMessage_legacyPretending(Client_MailServerExample client, Template template) {
        TemplateEngine templateEngine1 = getTemplateEngine1();
        MailServer mailServer1 = getMailServer1();
        String msgContent = templateEngine1.prepareMessage(template, client);
        mailServer1.send(client.getEmail(), msgContent);
    }

    MailServer getMailServer1() {
        return new MailServer();
    }

    TemplateEngine getTemplateEngine1() {
        return new TemplateEngine();
    }
}

