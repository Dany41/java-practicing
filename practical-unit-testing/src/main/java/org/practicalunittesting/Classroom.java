package org.practicalunittesting;

import java.time.DayOfWeek;
import java.util.List;

public interface Classroom {
    String getName();

    List<Equipment> getEquipment();

    int getCapacity();

    int getCleaningHour();

}
