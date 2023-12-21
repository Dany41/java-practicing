package org.adventofcode.day10;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"x", "y"})
public class Tile {

    int x;
    int y;
    Pipe pipe;
    GridTile context;
    Tile previous;


    public Tile(int x, int y, Pipe pipe, GridTile context, Tile previous) {
        this.x = x;
        this.y = y;
        this.pipe = pipe;
        this.context = context;
        this.previous = previous;
    }

    public Tile next() {
        return context.nextTile(this);
    }
}
