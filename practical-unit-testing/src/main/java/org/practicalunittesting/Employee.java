package org.practicalunittesting;

public class Employee {
    private final String name;
    private final String surname;
    private final Position position;
    private final Address homeAddress;
    private final Phone mobilePhone;
    private final Phone stationaryPhone;

    public Employee(String name, String surname, Position position, Address homeAddress, Phone mobilePhone, Phone stationaryPhone) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.homeAddress = homeAddress;
        this.mobilePhone = mobilePhone;
        this.stationaryPhone = stationaryPhone;
    }
}
