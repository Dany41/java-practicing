package org.kata.lift;

import lombok.Getter;

import java.io.PrintStream;

@Getter
public class Lift {
    private int currentFloor;
    private PrintStream printStream;

    public Lift(PrintStream printStream) {
        this.printStream = printStream;
        this.currentFloor = 1; // Start from the first floor by default
    }

    // Other methods for lift behaviors (e.g., responding to calls, delivering passengers, etc.)

    public boolean call(int floor, Direction direction) {
        printStream.println("Going to " + floor + " floor");
        this.currentFloor = floor;
        return true;
    }


}
