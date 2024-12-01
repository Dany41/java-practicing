package org.adventofcode.y2023.day10;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static org.adventofcode.Utils.getInput;
import static org.adventofcode.y2023.day10.Connection.*;
import static org.adventofcode.y2023.day10.Pipe.arePipesConnected;

public class Day10Task {
    public static void main(String[] args) {

        String[] input = getInput("day10_data.txt");

        int result1 = part1(input);
        int result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    private static int part1(String[] input) {
        int result = 0;
        Tile animalWithPipe = new GridTile(input).getAnimalTile();
        Tile currentTile = animalWithPipe.next();
        while (!currentTile.getPipe().equals(Pipe.ANIMAL)) {
            result++;
            currentTile = currentTile.next();
        }
        return (result + 1) / 2;
    }

    private static int part2(String[] input) {
        int result = 0;
        Tile animalWithPipe = new GridTile(input).getAnimalTile();
        Tile currentTile = animalWithPipe.next();
        Map<Pair<Integer, Integer>, Tile> loop = new HashMap<>();
        loop.put(Pair.of(animalWithPipe.getX(), animalWithPipe.getY()), animalWithPipe);
        loop.put(Pair.of(currentTile.getX(), currentTile.getY()), currentTile);
        while (!currentTile.getPipe().equals(Pipe.ANIMAL)) {
            currentTile = currentTile.next();
            loop.put(Pair.of(currentTile.getX(), currentTile.getY()), currentTile);
        }
        for (int i = 1; i < input.length - 1; i++) {
            for (int j = 1; j < input[i].length() - 1; j++) {
                if (tileIsNotInLoop(loop, j, i)) {
                    int countFromLeft = countBordersFromLeft(loop, j, i);
                    if (countFromLeft == 0) continue;
                    if (countFromLeft % 2 == 1) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private static int countBordersFromLeft(Map<Pair<Integer, Integer>, Tile> loop, int x, int y) {
        int result = 0;
        int pointer = x - 1;
        List<List<Tile>> listOfListTiles = new ArrayList<>();
        List<Tile> tiles = new ArrayList<>();
        while (pointer >= 0) {
            if (!tileIsNotInLoop(loop, pointer, y)) {
                if (loop.get(Pair.of(pointer, y)).getPipe().equals(Pipe.VERTICAL)) {
                    tiles.add(loop.get(Pair.of(pointer, y)));
                    pointer--;
                    listOfListTiles.add(tiles);
                    tiles = new ArrayList<>();
                } else {
                    if (notConnectedFromLeftAndRight(loop, pointer, y)) tiles.add(loop.get(Pair.of(pointer, y)));
                    pointer--;
                    while (!notConnectedFromLeftAndRight(loop, pointer, y) && !tileIsNotInLoop(loop, pointer, y)) {
                        tiles.add(loop.get(Pair.of(pointer, y)));
                        pointer--;
                    }
                    if (!tileIsNotInLoop(loop, pointer, y)) tiles.add(loop.get(Pair.of(pointer, y)));
                    if (!tiles.isEmpty()) {
                        listOfListTiles.add(tiles);
                        tiles = new ArrayList<>();
                    }
                    pointer--;
                }
            } else {
                pointer--;
            }
        }
        for (int i = 0; i < listOfListTiles.size(); i++) {
            List<Tile> consecutiveTiles = listOfListTiles.get(i);
            if (consecutiveTiles.size() == 1) {
                result++;
                continue;
            }
            if (
                    consecutiveTiles.get(0).previous.getY() != consecutiveTiles.get(consecutiveTiles.size() - 1).next().getY() ||
                            consecutiveTiles.get(0).next().getY() != consecutiveTiles.get(consecutiveTiles.size() - 1).previous.getY()
            ) {
                result++;
            } else {
                result += 2;
            }
        }
        return result;
    }

    private static boolean tileIsNotInLoop(Map<Pair<Integer, Integer>, Tile> loop, int x, int y) {
        return !loop.containsKey(Pair.of(x, y));
    }

    private static boolean notConnectedFromLeftAndRight(Map<Pair<Integer, Integer>, Tile> loop, int x, int y) {
        Tile leftTile = loop.getOrDefault(Pair.of(x - 1, y), null);
        Tile rightTile = loop.getOrDefault(Pair.of(x + 1, y), null);
        Tile currentTile = loop.get(Pair.of(x, y));
        if (leftTile != null && rightTile != null && currentTile != null) {
            return !(arePipesConnected(currentTile.getPipe(), leftTile.getPipe(), TO_LEFT) &&
                    arePipesConnected(currentTile.getPipe(), rightTile.getPipe(), TO_RIGHT));
        } else {
            return true;
        }
    }
}
