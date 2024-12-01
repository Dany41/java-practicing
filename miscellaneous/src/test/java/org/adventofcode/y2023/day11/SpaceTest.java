package org.adventofcode.y2023.day11;

import org.adventofcode.y2023.day11.Space;
import org.adventofcode.y2023.day11.Universe;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SpaceTest {

    public static final String[] INPUT_WITH_SINGLE_DOT = {
            "."
    };
    public static final String[] INPUT_WITH_DOTS = {
            "..",
            ".."
    };
    public static final String[] INPUT_WITH_SINGLE_UNIVERSE = {
            "#.",
            ".."
    };
    public static final String[] INPUT_FROM_EXAMPLE = {
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
    };

    @Test
    void spaceCanBeCreatedFromStringArray() {
        assertThatNoException().isThrownBy(() -> Space.class.getDeclaredConstructor(String[].class));
    }

    @Test
    void spaceExpandInputWithSingleDot() {
        Space space = new Space(INPUT_WITH_SINGLE_DOT);

        Long[][] expectedSpaceMap = {
                {4L}
        };

        assertThat(space.getMap()).isEqualTo(expectedSpaceMap);
    }
    @Test
    void spaceExpandInputWithDots() {
        Space space = new Space(INPUT_WITH_DOTS);

        Long[][] expectedSpaceMap = {
                {4L,4L},
                {4L,4L}
        };

        assertThat(space.getMap()).isEqualTo(expectedSpaceMap);
    }
    @Test
    void spaceExpandInputWithSingleUniverse() {
        Space space = new Space(INPUT_WITH_SINGLE_UNIVERSE);

        Long[][] expectedSpaceMap = {
                {0L,2L},
                {2L,4L}
        };

        assertThat(space.getMap()).isEqualTo(expectedSpaceMap);
    }

    @Test
    void spaceExpandInputFromExample() {
        Space space = new Space(INPUT_FROM_EXAMPLE);

        Long[][] expectedSpaceMap = {
                {1L,1L,2L,0L,1L,2L,1L,1L,2L,1L},
                {1L,1L,2L,1L,1L,2L,1L,0L,2L,1L},
                {0L,1L,2L,1L,1L,2L,1L,1L,2L,1L},
                {2L,2L,4L,2L,2L,4L,2L,2L,4L,2L},
                {1L,1L,2L,1L,1L,2L,0L,1L,2L,1L},
                {1L,0L,2L,1L,1L,2L,1L,1L,2L,1L},
                {1L,1L,2L,1L,1L,2L,1L,1L,2L,0L},
                {2L,2L,4L,2L,2L,4L,2L,2L,4L,2L},
                {1L,1L,2L,1L,1L,2L,1L,0L,2L,1L},
                {0L,1L,2L,1L,0L,2L,1L,1L,2L,1L}
        };

        assertThat(space.getMap()).isEqualTo(expectedSpaceMap);
    }

    @Test
    void spaceProvideEmptyListOfUniverses() {
        Space space = new Space(INPUT_WITH_SINGLE_DOT);

        List<Universe> expectedUniverses = Collections.emptyList();

        assertThat(space.getUniverses()).isEqualTo(expectedUniverses);
    }

    @Test
    void spaceProvideExpectedListOfUniverses() {
        Space space = new Space(INPUT_WITH_SINGLE_UNIVERSE);

        List<Universe> expectedUniverses = List.of(new Universe(0, 0));

        assertThat(space.getUniverses()).isEqualTo(expectedUniverses);
    }

    @Test
    void spaceProvideExpectedListOfUniversesFromExample() {
        Space space = new Space(INPUT_FROM_EXAMPLE);

        List<Universe> expectedUniverses = List.of(
                    new Universe(3, 0),
                    new Universe(7, 1),
                    new Universe(0, 2),
                    new Universe(6, 4),
                    new Universe(1, 5),
                    new Universe(9, 6),
                    new Universe(7, 8),
                    new Universe(0, 9),
                    new Universe(4, 9)
                );

        assertThat(space.getUniverses()).isEqualTo(expectedUniverses);
    }

}