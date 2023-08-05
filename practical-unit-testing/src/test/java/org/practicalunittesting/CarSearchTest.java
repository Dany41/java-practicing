package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarSearchTest {

    @Test
    void shouldFindSportCar() {
        CarSearch carSearch = new CarSearch();

        Engine engineA = mock(Engine.class);
        Manufacturer manufacturerA = mock(Manufacturer.class);
        Car carA = mock(Car.class);

        Engine engineB = mock(Engine.class);
        Manufacturer manufacturerB = mock(Manufacturer.class);
        Car sportCar = mock(Car.class);

        when(carA.getEngine()).thenReturn(engineA);
        when(carA.getManufacturer()).thenReturn(manufacturerA);
        when(sportCar.getColor()).thenReturn(Color.BLACK);
        when(engineA.getNbOfCylinders()).thenReturn(4);
        when(manufacturerA.getName()).thenReturn("NOT FERRARI");

        when(sportCar.getEngine()).thenReturn(engineB);
        when(sportCar.getManufacturer()).thenReturn(manufacturerB);
        when(sportCar.getColor()).thenReturn(Color.RED);
        when(engineB.getNbOfCylinders()).thenReturn(8);
        when(manufacturerB.getName()).thenReturn("Ferrari");

        carSearch.addCar(carA);
        carSearch.addCar(sportCar);

        assertThat(carSearch.findSportCars())
                .contains(sportCar)
                .doesNotContain(carA)
                .hasSize(1);

    }

}