package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RaceResultsServiceTest {

    private RaceResultsService raceResults = new RaceResultsService();
    private Client clientA = mock(Client.class);
    private Client clientB = mock(Client.class);
    private Message message = mock(Message.class);

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

}