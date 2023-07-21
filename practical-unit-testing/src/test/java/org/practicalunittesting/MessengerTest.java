package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class MessengerTest {

    private static final String CLIENT_EMAIL = "client@email.com";
    private static final String MSG_CONTENT = "Important message";

    TemplateEngine templateEngine;
    Client_MailServerExample client;
    MailServer mailServer;
    Template template;

    @BeforeEach
    void setUp() {
        templateEngine = mock(TemplateEngine.class);
        client = mock(Client_MailServerExample.class);
        mailServer = mock(MailServer.class);
        template = mock(Template.class);
    }

    @Test
    void testSendMessage() {
        when(templateEngine.prepareMessage(any(), any()))
                .thenReturn("Important message");

        assertThat(templateEngine.prepareMessage(new Template(), new Client_MailServerExample()))
                .isEqualTo("Important message");
    }

    @Test
    void testSendMessageConsecutiveCalls() {
        when(templateEngine.prepareMessage(any(), any()))
                .thenReturn("message 1", "message 2", "message 3");

        assertThat(templateEngine.prepareMessage(new Template(), new Client_MailServerExample())).isEqualTo("message 1");
        assertThat(templateEngine.prepareMessage(new Template(), new Client_MailServerExample())).isEqualTo("message 2");
        assertThat(templateEngine.prepareMessage(new Template(), new Client_MailServerExample())).isEqualTo("message 3");
    }

    @Test
    void verifySendMessageMethodActuallySends() {

        when(client.getEmail()).thenReturn("some-email@gmail.com");
        when(templateEngine.prepareMessage(any(), eq(client))).thenReturn("Important message");

        new Messenger(mailServer, templateEngine).sendMessage(client, new Template());
        verify(mailServer).send("some-email@gmail.com", "Important message");

    }

    @Test
    void shouldSendEmail() {
        Messenger sut = new Messenger(mailServer, templateEngine);

        when(client.getEmail()).thenReturn(CLIENT_EMAIL);
        when(templateEngine.prepareMessage(template, client)).thenReturn(MSG_CONTENT);

        sut.sendMessage(client, template);

        verify(mailServer).send(CLIENT_EMAIL, MSG_CONTENT);
    }

}