package org.practicalunittesting;

import java.awt.*;

public interface Car {
    boolean needsFuel();
    Engine getEngine();
    Color getColor();
    Manufacturer getManufacturer();

    boolean isSportCar();
}
