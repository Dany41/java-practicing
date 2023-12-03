package org.kata.bugszero;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {

    List<Player> players = new ArrayList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player player = players.get(currentPlayer);
        System.out.println(player + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.isInJail()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(player + " is getting out of the penalty box");
                movePlayerAndAskQuestion(roll, player);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            movePlayerAndAskQuestion(roll, player);
        }

    }

    private void movePlayerAndAskQuestion(int roll, Player player) {
        player.setCurrentPlace(player.getCurrentPlace().getIntValue() + roll);
        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + player.getCurrentPlace().name());
        System.out.println("The category is " + player.currentCategory());
        askQuestion(player);
    }

    private void askQuestion(Player player) {
        System.out.println(player.currentCategory().nextQuestion());
    }

    public boolean wasCorrectlyAnswered() {
        Player player = players.get(currentPlayer);
        if (player.isInJail()){
            if (isGettingOutOfPenaltyBox) {
                actionsForCorrectAnswer(player);
                return didPlayerWin();
            } else {
                nextPlayer();
                return true;
            }

        } else {
            actionsForCorrectAnswer(player);
            return didPlayerWin();
        }
    }

    private void actionsForCorrectAnswer(Player player) {
        System.out.println("Answer was correct!!!!");
        nextPlayer();
        player.addCoin();
        System.out.println(player + " now has " + player.getPurses() + " Gold Coins.");
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer(){
        Player player = players.get(currentPlayer);
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
        player.setInJail(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        Player player = players.get(currentPlayer);
        return !(player.getPurses() == 6);
    }

    private class Player {
        @Getter
        private Place currentPlace = Place.ZEROTH;
        @Getter
        private final String name;
        @Setter
        @Getter
        private boolean isInJail = false;
        private int purses = 0;

        public Player(String name) {
            this.name = name;
        }

        public void addCoin() {
            this.purses++;
        }

        public int getPurses() {
            return purses;
        }

        @Override
        public String toString() {
            return name;
        }

        public void setCurrentPlace(int placeIntValueToSet) {
            int actualIntValue = placeIntValueToSet > 11 ? placeIntValueToSet - 12 : placeIntValueToSet;
            this.currentPlace = Place.fromInt(actualIntValue);
        }

        public Category currentCategory() {
            return this.currentPlace.getCategory();
        }
    }

    private enum Category {
        POP, SCIENCE, SPORTS, ROCK;

        final List<String> questions = new LinkedList<>();

        Category() {
            for (int i = 0; i < 50; i++) {
                questions.add(this.name() + " Question " + i);
            }
        }

        public String nextQuestion() {
            return questions.remove(0);
        }
    }

    @Getter
    private enum Place {
        ZEROTH(0, Category.POP),
        FIRST(1, Category.SCIENCE),
        SECOND(2, Category.SPORTS),
        THIRD(3, Category.ROCK),
        FOURTH(4, Category.POP),
        FIFTH(5, Category.SCIENCE),
        SIXTH(6, Category.SPORTS),
        SEVENTH(7, Category.ROCK),
        EIGHTS(8, Category.POP),
        NINTH(9, Category.SCIENCE),
        TENTH(10, Category.SPORTS),
        ELEVENTH(11, Category.ROCK);

        private final Category category;
        private final int intValue;

        Place(int i, Category category) {
            this.intValue = i;
            this.category = category;
        }

        public static Place fromInt(int i) {
            return Arrays.stream(values()).filter(place -> place.getIntValue() == i).toList().get(0);
        }
    }
}
