package org.adventofcode.y2023.day16;

public class Beam {
    private BeamHeading beamHeading;
    private int x;
    private int y;

    public Beam(BeamHeading beamHeading) {
        this.beamHeading = beamHeading;
        this.x = 0;
        this.y = 0;
    }

    public Beam() {
        this(BeamHeading.RIGHT);
    }

    public void enterLayout(char[][] layout) {

        this.beamHeading.getMoveFn().accept(x, y);
    }

    private char currentTile(char[][] layout) {
        return layout[this.y][this.x];
    }
}
