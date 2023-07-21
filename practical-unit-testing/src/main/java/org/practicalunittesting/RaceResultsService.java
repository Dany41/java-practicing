package org.practicalunittesting;

import java.util.ArrayList;
import java.util.Collection;

public class RaceResultsService {

    private Collection<Client> clients = new ArrayList<>();

    public void addSubscriber(Client client) {
        this.clients.add(client);
    }

    public void send(Message message) {
        clients.forEach(client -> client.receive(message));
    }
}
