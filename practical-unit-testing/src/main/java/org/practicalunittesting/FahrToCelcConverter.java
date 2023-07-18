package org.practicalunittesting;

public class FahrToCelcConverter {

    public static int toFahrenheit(int celcius) {
        return celcius * 9 / 5 + 32;
    }

    public static int toCelcius(int fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
}
