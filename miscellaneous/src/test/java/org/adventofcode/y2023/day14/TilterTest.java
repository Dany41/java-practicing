package org.adventofcode.y2023.day14;

import org.adventofcode.y2023.day14.TiltDirection;
import org.adventofcode.y2023.day14.Tilter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TilterTest {

    @Test
    void tiltInNorthMovesControlPanelWithOneRock() {
        char[][] input = new char[][]{
                {'.'},
                {'O'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'O'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthMCorrectlyMovesRockInMultiLevelControlPanel() {
        char[][] input = new char[][]{
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'O'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'O'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthMCorrectlyMovesRockInMultiLevelControlPanelWithOneCube() {
        char[][] input = new char[][]{
                {'.'},
                {'.'},
                {'.'},
                {'#'},
                {'.'},
                {'.'},
                {'.'},
                {'O'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'.'},
                {'.'},
                {'.'},
                {'#'},
                {'O'},
                {'.'},
                {'.'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthMDoesNotMoveRockIfItLiesOnCube() {
        char[][] input = new char[][]{
                {'#'},
                {'O'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'#'},
                {'O'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthDoesNotMoveRockIfItLiesOnCubeInMultilevelControlPanel() {
        char[][] input = new char[][]{
                {'.'},
                {'.'},
                {'#'},
                {'O'},
                {'.'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'.'},
                {'.'},
                {'#'},
                {'O'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthMovesRockInMultilevelControlPanelWithCibes() {
        char[][] input = new char[][]{
                {'.'},
                {'#'},
                {'.'},
                {'#'},
                {'.'},
                {'.'},
                {'O'},
                {'.'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'.'},
                {'#'},
                {'.'},
                {'#'},
                {'O'},
                {'.'},
                {'.'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }

    @Test
    void tiltInNorthMovesRocksInMultilevelControlPanelWithCibes() {
        char[][] input = new char[][]{
                {'.'},
                {'#'},
                {'.'},
                {'O'},
                {'.'},
                {'#'},
                {'.'},
                {'.'},
                {'O'},
                {'.'}
        };
        Tilter tilter = new Tilter(input);
        char[][] expectedResult = new char[][]{
                {'.'},
                {'#'},
                {'O'},
                {'.'},
                {'.'},
                {'#'},
                {'O'},
                {'.'},
                {'.'},
                {'.'}
        };
        assertThat(tilter.tilt(TiltDirection.NORTH)).isEqualTo(expectedResult);
    }
}