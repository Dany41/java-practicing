package org.adventofcode.y2023.day14;

import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class Tilter {

    private ControlPanel controlPanel;

    public Tilter(char[][] input) {
        this.controlPanel = new ControlPanel(input);
    }

    public Tilter(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public char[][] tilt(TiltDirection tiltDirection) {
        for (int i = 0; i < this.controlPanel.getRocks().size(); i++) {
            Pair<Integer, Integer> rock = this.controlPanel.getRocks().get(i);
            int rockHeight = rock.getRight();
            int rockHorizontalPlace = rock.getLeft();
            int newHeight = Stream.concat(this.controlPanel.getRocks().stream()
                    .filter(r -> r.getLeft().equals(rockHorizontalPlace))
                    .filter(r -> r.getRight() < rockHeight),
                    this.controlPanel.getCubes().stream()
                    .filter(r -> r.getLeft().equals(rockHorizontalPlace))
                    .filter(r -> r.getRight() < rockHeight))
                    .mapToInt(Pair::getRight).max().orElse(-1) + 1;
            this.controlPanel.set('.', rockHorizontalPlace, rockHeight);
            this.controlPanel.set('O', rockHorizontalPlace, newHeight);
            this.controlPanel.setRock(i, Pair.of(rockHorizontalPlace, newHeight));
        }
        return this.controlPanel.getGrid();
    }

}
