package org.kata.gossipingbusdrivers;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GossipingBusDriversTest {

    private final int INVALID_VALUE = -1;

    @Test
    void returnsInvalidValueForEmptyListOfDrivers() {
        List<Driver> drivers = Collections.emptyList();
        assertThat(GossipingBusDrivers.calculate(drivers)).isEqualTo(INVALID_VALUE);
    }

    @Test
    void returnsZeroIfThereIsOnlyOneDriver() {
        List<Driver> drivers = List.of(new Driver(1, new int[]{1, 2, 3}));
        assertThat(GossipingBusDrivers.calculate(drivers)).isOne();
    }

    @Test
    void correctlyCalculatesForDriversWithSameRouteLengths() {
        Driver driver1 = new Driver(1, new int[]{1, 2, 3});
        Driver driver2 = new Driver(2, new int[]{5, 1, 3});
        List<Driver> drivers = List.of(driver1, driver2);
        assertThat(GossipingBusDrivers.calculate(drivers)).isEqualTo(3);
    }

    @Test
    void correctlyCalculatesForDriversWithDifferentRouteLengths() {
        Driver driver1 = new Driver(1, new int[]{3, 1, 2, 3});
        Driver driver2 = new Driver(2, new int[]{3, 2, 3, 1});
        Driver driver3 = new Driver(3, new int[]{4, 2, 3, 4, 5});
        List<Driver> drivers = List.of(driver1, driver2, driver3);
        assertThat(GossipingBusDrivers.calculate(drivers)).isEqualTo(5);
    }

    @Test
    void calculatesInvalidValueForDriversWithNotMatchingRoutes() {
        Driver driver1 = new Driver(1, new int[]{2, 1, 2});
        Driver driver2 = new Driver(2, new int[]{5, 2, 8});
        List<Driver> drivers = List.of(driver1, driver2);
        assertThat(GossipingBusDrivers.calculate(drivers)).isEqualTo(-1);
    }


}