package org.practicalunittesting;

import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {

    private Collection<Client> clients = new HashSet<>();

    public void addSubscriber(Client client) {
        this.clients.add(client);
    }

    public void send(Message message) {
        clients.forEach(client -> client.receive(message));
    }

    public void removeSubscriber(Client client) {
        clients.remove(client);
    }

    public void addSubscriber(Client clientA, RaceCategory raceCategory) {

    }

    public void send(Message message, RaceCategory raceCategory) {

    }
}

enum RaceCategory {
    HORSE, F1, BOAT, ALL
}
