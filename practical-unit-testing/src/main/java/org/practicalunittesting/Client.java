package org.practicalunittesting;

public interface Client {
    void receive(Message message);

    void receive(Message message, RaceCategory raceCategory);
}
