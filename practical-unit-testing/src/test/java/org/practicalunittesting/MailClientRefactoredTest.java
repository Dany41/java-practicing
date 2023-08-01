package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MailClientRefactoredTest {

    private static final String ANY_ADDRESS = "address";
    private static final String ANY_TITLE = "title";
    private static final String ANY_BODY = "body";

    @Test
    void sendEmailShouldSendGivenEmail() {
        EmailServerRefactored emailServerRefactored = mock(EmailServerRefactored.class);
        MailClientRefactored mailClientRefactored = new MailClientRefactored(emailServerRefactored);
        mailClientRefactored.sendEmail(ANY_ADDRESS, ANY_TITLE, ANY_BODY);

        verify(emailServerRefactored).sendEmail(argThat(e ->
                e.getAddress().equals(ANY_ADDRESS) && e.getBody().equals(ANY_BODY) && e.getTitle().equals(ANY_TITLE)));
    }

}