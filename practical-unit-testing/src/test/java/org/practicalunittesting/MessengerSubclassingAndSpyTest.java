package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MessengerSubclassingAndSpyTest {

    TemplateEngine templateEngine = mock(TemplateEngine.class);
    MailServer mailServer = mock(MailServer.class);
    Client_MailServerExample client = mock(Client_MailServerExample.class);

    class MessengerUnitTestingHelper extends Messenger {

        @Override
        MailServer getMailServer1() {
            return mailServer;
        }

        @Override
        TemplateEngine getTemplateEngine1() {
            return templateEngine;
        }
    }

    @Test
    void sendMessage_legacyPretending_extendingExample() {
        MessengerUnitTestingHelper messenger = new MessengerUnitTestingHelper();
        when(client.getEmail()).thenReturn("some-email@gmail.com");
        when(templateEngine.prepareMessage(any(), eq(client))).thenReturn("Important message");

        messenger.sendMessage_legacyPretending(client, new Template());
        verify(mailServer).send("some-email@gmail.com", "Important message");
    }

    @Test
    void sendMessage_legacyPretending_spyExample() {
        Messenger messenger = spy(new Messenger());

        doReturn(templateEngine).when(messenger).getTemplateEngine1();
        doReturn(mailServer).when(messenger).getMailServer1();
        when(client.getEmail()).thenReturn("some-email@gmail.com");
        when(templateEngine.prepareMessage(any(), eq(client))).thenReturn("Important message");

        messenger.sendMessage_legacyPretending(client, new Template());
        verify(mailServer).send("some-email@gmail.com", "Important message");
    }
}