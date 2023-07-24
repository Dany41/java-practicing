package org.practicalunittesting;

import java.time.LocalDateTime;
import java.util.*;

public class RaceResultsService {

    private Map<Client, List<RaceCategory>> clients = new HashMap<>();

    private Logger logger;

    public RaceResultsService(Logger logger) {
        this.logger = logger;
    }

    public void addSubscriber(Client client) {
        List<RaceCategory> categories = new ArrayList<>();
        categories.add(RaceCategory.ALL);
        this.clients.put(client, categories);
    }

    public void send(Message message, RaceCategory raceCategory) {
        logger.log(LocalDateTime.now() + ": a message is sent: " + message);
        clients.forEach((client, categories) -> {
            if (categories.contains(RaceCategory.ALL)) {
                client.receive(message);
            } else if (categories.contains(raceCategory)) {
                client.receive(message, raceCategory);
            }
        });
    }

    public void removeSubscriber(Client client) {
        if (clients.containsKey(client)) {
            clients.remove(client);
        } else {
            throw new IllegalActionException("Unsubscribed client cannot be unsubscribed!");
        }
    }

    public void addSubscriber(Client client, RaceCategory raceCategory) {
        List<RaceCategory> newCategories = new ArrayList<>();
        List<RaceCategory> raceCategories = clients.get(client);
        if (raceCategories == null) {
            newCategories.add(raceCategory);
            clients.put(client, newCategories);
        } else if (!raceCategories.contains(RaceCategory.ALL) && !raceCategory.equals(RaceCategory.ALL)){
            raceCategories.add(raceCategory);
        } else {
            newCategories.add(RaceCategory.ALL);
            clients.put(client, newCategories);
        }
    }

    public void send(Message message) {
        send(message, RaceCategory.ALL);
    }
}

