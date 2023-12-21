package org.adventofcode.day10;

import static org.adventofcode.day10.Connection.*;

public class GridTile {

    String[] grid;
    int width;
    int height;

    public GridTile(String[] grid) {
        this.grid = grid;
        this.width = grid[0].length();
        this.height = grid.length;
    }

    public Tile getAnimalTile() {
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'S') return new Tile(j, i, Pipe.ANIMAL, this, null);
            }
        }
        return new Tile(0, 0, Pipe.GROUND, this, null);
    }

    public Tile nextTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        if (y > 0) {
            Pipe pipe = Pipe.fromChar(grid[y - 1].toCharArray()[x]);
            Tile nextTile = new Tile(x, y - 1, pipe, this, tile);
            if (Pipe.arePipesConnected(tile.getPipe(), pipe, TO_TOP) && !nextTile.equals(tile.previous)) {
                return nextTile;
            }
        }
        if (x < width - 1) {
            Pipe pipe = Pipe.fromChar(grid[y].toCharArray()[x + 1]);
            Tile nextTile = new Tile(x + 1, y, pipe, this, tile);
            if (Pipe.arePipesConnected(tile.getPipe(), pipe, TO_RIGHT) && !nextTile.equals(tile.previous)) {
                return nextTile;
            }
        }
        if (y < height - 1) {
            Pipe pipe = Pipe.fromChar(grid[y + 1].toCharArray()[x]);
            Tile nextTile = new Tile(x, y + 1, pipe, this, tile);
            if (Pipe.arePipesConnected(tile.getPipe(), pipe, TO_BOTTOM) && !nextTile.equals(tile.previous)) {
                return nextTile;
            }
        }
        if (x > 0) {
            Pipe pipe = Pipe.fromChar(grid[y].toCharArray()[x - 1]);
            Tile nextTile = new Tile(x - 1, y, pipe, this, tile);
            if (Pipe.arePipesConnected(tile.getPipe(), pipe, TO_LEFT) && !nextTile.equals(tile.previous)) {
                return nextTile;
            }
        }
        return new Tile(0, 0, Pipe.GROUND, this, tile);
    }
}
