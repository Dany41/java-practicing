package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarSearchRefactoredTest {

    @Test
    void findSportCars() {
        CarSearchRefactored carSearch = new CarSearchRefactored();
        Car carA = mock(Car.class);
        Car sportCar = mock(Car.class);

        when(carA.isSportCar()).thenReturn(false);
        when(sportCar.isSportCar()).thenReturn(true);

        carSearch.addCar(carA);
        carSearch.addCar(sportCar);

        assertThat(carSearch.findSportCars())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(sportCar)
                .doesNotContain(carA);

    }
}