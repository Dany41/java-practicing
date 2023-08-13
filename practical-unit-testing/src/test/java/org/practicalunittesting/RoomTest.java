package org.practicalunittesting;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomTest {

    Condition<Room> OwnedByMartha = new Condition<>(
            room -> room.getOwner().equals("Martha"),
            "should be owned by Martha");

    int expectedWalls;

    Condition<Room> ExpectedCountOfWalls = new Condition<>(
            room -> room.getWallsN().equals(expectedWalls),
            "room should have 4 walls");

    @Test
    void testWithACondition() {
        Room room = mock(Room.class);
        when(room.getOwner()).thenReturn("Martha");
        when(room.getWallsN()).thenReturn(4);

        expectedWalls = 4;

        assertThat(room).is(OwnedByMartha);
        assertThat(room).has(ExpectedCountOfWalls);
    }

}