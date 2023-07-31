package org.practicalunittesting;

import java.util.Date;
import java.util.Objects;

public class Meeting implements Event {
    private final Date startDate;

    private final Date endDate;

    public Meeting(Date startDate, Date endDate) {
        this.startDate = new Date(startDate.getTime());
        this.endDate = new Date(endDate.getTime());
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(startDate, meeting.startDate) && Objects.equals(endDate, meeting.endDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
