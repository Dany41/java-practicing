package org.adventofcode.y2023.day7;

import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Data
public class Hand {

    private char[] cards;
    private HandTypePart1 handTypePart1;
    private HandTypePart2 handTypePart2;

    public Hand(char[] cards) {
        this.cards = cards;
        this.handTypePart1 = HandTypePart1.getHandTypeFromHand(this);
        this.handTypePart2 = HandTypePart2.getHandTypeFromHand(this);
    }

    private static Map<Character, Integer> cardSymbolToStrengthPart1 = new HashMap<>();

    static {
        cardSymbolToStrengthPart1.put('A', 13);
        cardSymbolToStrengthPart1.put('K', 12);
        cardSymbolToStrengthPart1.put('Q', 11);
        cardSymbolToStrengthPart1.put('J', 10);
        cardSymbolToStrengthPart1.put('T', 9);
        cardSymbolToStrengthPart1.put('9', 8);
        cardSymbolToStrengthPart1.put('8', 7);
        cardSymbolToStrengthPart1.put('7', 6);
        cardSymbolToStrengthPart1.put('6', 5);
        cardSymbolToStrengthPart1.put('5', 4);
        cardSymbolToStrengthPart1.put('4', 3);
        cardSymbolToStrengthPart1.put('3', 2);
        cardSymbolToStrengthPart1.put('2', 1);
    }

    private static final Comparator<? super char[]> CARDS_COMPARATOR_PART1 = (char1, char2) -> {
        for (int i = 0; i < char1.length; i++) {
            if (char1[i] == char2[i]) continue;
            return cardSymbolToStrengthPart1.get(char1[i]) < cardSymbolToStrengthPart1.get(char2[i]) ? -1 : 1;
        }
        return 0;
    };
    public static final Comparator<? super Hand> CHARS_COMPARATOR_PART1 = (hand1, hand2) -> {
        int typeComp = Integer.compare(hand1.getHandTypePart1().getStrength(), hand2.getHandTypePart1().getStrength());
        return typeComp == 0 ? CARDS_COMPARATOR_PART1.compare(hand1.getCards(), hand2.getCards()) : typeComp;
    };

    private static Map<Character, Integer> cardSymbolToStrengthPart2 = new HashMap<>();

    static {
        cardSymbolToStrengthPart2.put('A', 13);
        cardSymbolToStrengthPart2.put('K', 12);
        cardSymbolToStrengthPart2.put('Q', 11);
        cardSymbolToStrengthPart2.put('T', 10);
        cardSymbolToStrengthPart2.put('9', 9);
        cardSymbolToStrengthPart2.put('8', 8);
        cardSymbolToStrengthPart2.put('7', 7);
        cardSymbolToStrengthPart2.put('6', 6);
        cardSymbolToStrengthPart2.put('5', 5);
        cardSymbolToStrengthPart2.put('4', 4);
        cardSymbolToStrengthPart2.put('3', 3);
        cardSymbolToStrengthPart2.put('2', 2);
        cardSymbolToStrengthPart2.put('J', 1);
    }

    private static final Comparator<? super char[]> CARDS_COMPARATOR_PART2 = (char1, char2) -> {
        for (int i = 0; i < char1.length; i++) {
            if (char1[i] == char2[i]) continue;
            return cardSymbolToStrengthPart2.get(char1[i]) < cardSymbolToStrengthPart2.get(char2[i]) ? -1 : 1;
        }
        return 0;
    };
    public static final Comparator<? super Hand> CHARS_COMPARATOR_PART2 = (hand1, hand2) -> {
        int typeComp = Integer.compare(hand1.getHandTypePart2().getStrength(), hand2.getHandTypePart2().getStrength());
        return typeComp == 0 ? CARDS_COMPARATOR_PART2.compare(hand1.getCards(), hand2.getCards()) : typeComp;
    };
}
