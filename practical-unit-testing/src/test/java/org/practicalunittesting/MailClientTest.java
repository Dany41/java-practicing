package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

class MailClientTest {

    private static final String ANY_ADDRESS = "address";
    private static final String ANY_TITLE = "title";
    private static final String ANY_BODY = "body";

    @Test
    void checkIfEmailIsSent() {
        MailClient mailClient = new MailClient();
        MockedStatic<EmailServer> emailServer = mockStatic(EmailServer.class);
        ArgumentCaptor<Email> emailArgumentCaptor = ArgumentCaptor.forClass(Email.class);

        mailClient.sendEmail(ANY_ADDRESS, ANY_TITLE, ANY_BODY);

        emailServer.verify(() -> EmailServer.sendEmail(emailArgumentCaptor.capture()));
        Email email = emailArgumentCaptor.getValue();

        assertThat(email.getAddress()).isEqualTo(ANY_ADDRESS);
        assertThat(email.getTitle()).isEqualTo(ANY_TITLE);
        assertThat(email.getBody()).isEqualTo(ANY_BODY);

        emailServer.close();
    }

}