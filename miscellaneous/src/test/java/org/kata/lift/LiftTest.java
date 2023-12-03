package org.kata.lift;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LiftTest {

    private PrintStream printStream = mock(PrintStream.class);
    private Lift lift;

    @BeforeEach
    void setUp() {
        lift = new Lift(printStream);
    }

    @Test
    void liftShouldStartFromFirstFloor() {
        assertThat(1).isEqualTo(lift.getCurrentFloor());
    }

    @Test
    void liftRespondsToCallWithSourceFloorAndDirection() {
        assertThat(lift.call(2, Direction.UP)).isTrue();
        verify(printStream).println("Going to 2 floor");
    }

    @Test
    void liftChangesFloorAfterCall() {
        assertThat(lift.call(2, Direction.UP)).isTrue();
        assertThat(lift.getCurrentFloor()).isEqualTo(2);
    }

}