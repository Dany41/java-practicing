package org.practicalunittesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class RaceResultsServiceTest {

    private Client clientA = mock(Client.class);
    private Client clientB = mock(Client.class);
    private Message message = mock(Message.class);
    private Logger logger = mock(Logger.class);
    private RaceResultsService raceResults = new RaceResultsService(logger);


    @Test
    void nonSubscribedClientShouldNotReceiveMessage() {
        raceResults.send(message);

        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    void subscribedClientShouldReceiveMessage() {
        raceResults.addSubscriber(clientA);
        raceResults.send(message);

        verify(clientA).receive(message);
    }

    @Test
    void messageShouldBeSentToAllSubscribedClients() {
        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientB);

        raceResults.send(message);

        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }

    @Test
    void shouldSendOnlyOneMessageToMultiSubscriber() {
        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientA);

        raceResults.send(message);

        verify(clientA).receive(message);
    }

    @Test
    void unsubscribedClientShouldNotReceiveMessages() {
        raceResults.addSubscriber(clientA);
        raceResults.removeSubscriber(clientA);

        raceResults.send(message);

        verify(clientA, never()).receive(message);
    }

    @Test
    void subscriberSignedForSpecificCategoryShouldReceiveOnlyMessagesWithThatCategory() {
        raceResults.addSubscriber(clientA, RaceCategory.HORSE);

        raceResults.send(message, RaceCategory.HORSE);
        raceResults.send(message, RaceCategory.F1);

        verify(clientA).receive(message, RaceCategory.HORSE);
        verify(clientA, never()).receive(message, RaceCategory.F1);
    }

    @Test
    void eachMessageSendingMustBeLogged() {
        raceResults.send(message);

        verify(logger, atLeastOnce()).log(any());
    }

    @Test
    void clientsReceivesMultipleMessages() {
        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientB, RaceCategory.HORSE);
        raceResults.addSubscriber(clientB, RaceCategory.F1);

        raceResults.send(message, RaceCategory.F1);
        raceResults.send(message, RaceCategory.BOAT);
        raceResults.send(message, RaceCategory.HORSE);
        raceResults.send(message, RaceCategory.ALL);

        verify(clientA, times(4)).receive(message);
        verify(clientB, times(2)).receive(eq(message), any());
    }

    @Test
    void throwIllegalActionExceptionWhenUnsubscribedClientUnsubscribed() {
        assertThatExceptionOfType(IllegalActionException.class)
                .isThrownBy(() -> raceResults.removeSubscriber(clientA))
                .withMessage("Unsubscribed client cannot be unsubscribed!");
    }

}