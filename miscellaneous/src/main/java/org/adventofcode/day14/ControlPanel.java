package org.adventofcode.day14;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;

@Getter
public class ControlPanel {

    private char[][] grid;
    private int height;
    private int width;
    private List<Pair<Integer, Integer>> rocks;
    private List<Pair<Integer, Integer>> cubes;

    public ControlPanel(char[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;
        List<Pair<Integer, Integer>> rocks = new LinkedList<>();
        List<Pair<Integer, Integer>> cubes = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (this.grid[i][j] == 'O') rocks.add(Pair.of(j, i));
                if (this.grid[i][j] == '#') cubes.add(Pair.of(j, i));
            }
        }
        this.rocks = rocks;
        this.cubes = cubes;
    }

    public char get(int width, int height) {
        return this.grid[height][width];
    }

    public void set(char newChar, int width, int height) {
        this.grid[height][width] = newChar;
    }

    public void setRock(int height, Pair<Integer, Integer> rock) {
        this.rocks.set(height, rock);
    }

    public int calculatePressure() {
        return this.rocks.stream()
                .mapToInt(r -> height - r.getRight())
                .sum();
    }
}
