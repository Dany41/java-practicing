package org.kata.gossipingbusdrivers;

import one.util.streamex.EntryStream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GossipingBusDrivers {

    public static int calculate(List<Driver> drivers) {
        if (drivers == null || drivers.size() == 0) return -1;
        Set<Integer> driverIds = drivers.stream().map(Driver::getId).collect(Collectors.toSet());
        for (int i = 0; i < 480; i++) {
            int index = i;
            EntryStream.of(drivers.stream()
                    .collect(Collectors.groupingBy(driver -> driver.getCurrentStop(index))))
                    .filterValues(list -> list.size() > 1)
                    .forKeyValue((key, list) -> list.forEach(driver -> driver.exchangeGossips(list)));
            if (drivers.stream().allMatch(driver -> driver.getGossipedDrivers().containsAll(driverIds))) return i + 1;
        }
        return -1;
    }
}
