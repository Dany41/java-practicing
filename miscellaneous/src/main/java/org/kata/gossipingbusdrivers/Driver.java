package org.kata.gossipingbusdrivers;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Driver {

    private int[] route;

    private int id;
    private Set<Integer> gossipedDrivers;

    public Driver(int id, int[] route) {
        this.id = id;
        this.route = route;
        this.gossipedDrivers = new HashSet<>();
        this.gossipedDrivers.add(this.id);
    }

    public int getCurrentStop(int i) {
        return route[i % route.length];
    }

    public void exchangeGossips(List<Driver> otherDrivers) {
        Set<Integer> gossips = otherDrivers.stream().flatMap(driver -> driver.getGossipedDrivers().stream()).collect(Collectors.toSet());
        this.gossipedDrivers.addAll(gossips);
    }
}
