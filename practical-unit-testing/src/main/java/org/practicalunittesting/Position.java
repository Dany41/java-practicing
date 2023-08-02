package org.practicalunittesting;

import java.util.Date;

public class Position {
    private final String position;
    private final Date startDate;
    private final Date endDate;

    public Position(String position, Date startDate, Date endDate) {
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
