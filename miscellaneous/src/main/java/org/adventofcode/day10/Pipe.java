package org.adventofcode.day10;

import java.util.Collections;
import java.util.Set;

public enum Pipe {
    ANIMAL, VERTICAL, HORIZONTAL, NORTH_EAST, NORTH_WEST, SOUTH_WEST, SOUTH_EAST, GROUND;

    public static Pipe fromChar(char c) {
        switch (c) {
            case '|' -> {
                return VERTICAL;
            }
            case '-' -> {
                return HORIZONTAL;
            }
            case 'L' -> {
                return NORTH_EAST;
            }
            case 'J' -> {
                return NORTH_WEST;
            }
            case '7' -> {
                return SOUTH_WEST;
            }
            case 'F' -> {
                return SOUTH_EAST;
            }
            case '.' -> {
                return GROUND;
            }
            case 'S' -> {
                return ANIMAL;
            }
            default -> throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    public static boolean arePipesConnected(Pipe pipe1, Pipe pipe2, Connection connection) {
        return pipe1.getConnections(connection).contains(pipe2);
    }

    private Set<Pipe> getConnections(Connection connection) {
        switch (connection) {
            case TO_TOP -> {
                return switch (this) {
                    case ANIMAL -> Set.of(VERTICAL, SOUTH_WEST, SOUTH_EAST);
                    case VERTICAL, NORTH_EAST, NORTH_WEST -> Set.of(VERTICAL, SOUTH_WEST, SOUTH_EAST, ANIMAL);
                    case HORIZONTAL, SOUTH_WEST, SOUTH_EAST, GROUND -> Collections.emptySet();
                };
            }
            case TO_RIGHT -> {
                return switch (this) {
                    case ANIMAL -> Set.of(HORIZONTAL, NORTH_WEST, SOUTH_WEST);
                    case HORIZONTAL, NORTH_EAST, SOUTH_EAST -> Set.of(HORIZONTAL, NORTH_WEST, SOUTH_WEST, ANIMAL);
                    case VERTICAL, NORTH_WEST, SOUTH_WEST, GROUND -> Collections.emptySet();
                };
            }
            case TO_BOTTOM -> {
                return switch (this) {
                    case ANIMAL -> Set.of(VERTICAL, NORTH_EAST, NORTH_WEST);
                    case VERTICAL, SOUTH_WEST, SOUTH_EAST -> Set.of(VERTICAL, NORTH_EAST, NORTH_WEST, ANIMAL);
                    case HORIZONTAL, NORTH_EAST, NORTH_WEST, GROUND -> Collections.emptySet();
                };
            }
            case TO_LEFT -> {
                return switch (this) {
                    case ANIMAL -> Set.of(HORIZONTAL, NORTH_EAST, SOUTH_EAST);
                    case HORIZONTAL, NORTH_WEST, SOUTH_WEST -> Set.of(HORIZONTAL, NORTH_EAST, SOUTH_EAST, ANIMAL);
                    case VERTICAL, NORTH_EAST, SOUTH_EAST, GROUND -> Collections.emptySet();
                };
            }
            case ANY -> {
                return Collections.emptySet();
            }
        }
        return Collections.emptySet();
    }
}
