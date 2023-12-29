package org.adventofcode.day16;

import lombok.Getter;

import java.util.function.BiConsumer;

@Getter
public enum BeamHeading {
    RIGHT((x, y) -> x++), BOTTOM((x, y) -> y++), LEFT((x, y) -> x--), TOP((x, y) -> y--);

    private final BiConsumer<Integer, Integer> moveFn;

    BeamHeading(BiConsumer<Integer, Integer> moveFn) {
        this.moveFn = moveFn;
    }
}
