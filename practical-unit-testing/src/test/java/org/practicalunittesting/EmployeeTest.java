package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

public class EmployeeTest {
    private static Phone MOBILE_PHONE = new Phone("123-456-789");
    private static Phone STATIONARY_PHONE = new Phone("123-456-789");
    private static Address HOME_ADDRESS = new Address("any street");

    private Employee createEmployee(String name, String surname,
                                    Position position) {
        return new Employee(name, surname, position,
                HOME_ADDRESS, MOBILE_PHONE, STATIONARY_PHONE);
    }

    @Test
    void ceoCanDoEverything() {
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 3, 1);
        Date startCeo = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date endCeo = cal.getTime();
        Position pm = new Position("ceo", startCeo, endCeo);
        Employee ceoEmpl = createEmployee("ceoName", "ceoSurname", pm);
        // some methods execution and assertions here
    }
    @Test
    void pmCanDoALot() {
        Calendar cal = Calendar.getInstance();
        cal.set(2008, 7, 12);
        Date startPm = cal.getTime();
        cal.add(Calendar.YEAR, 3);
        Date endPm = cal.getTime();
        Position pm = new Position("pm", startPm, endPm);
        Employee pmEmpl = createEmployee("pmName", "pmSurname", pm);
        // some methods execution and assertions here
    }

}
