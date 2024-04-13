package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseInstanceFieldsInsteadOfOrdinals implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Use instance fields instead of ordinals";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "all the enums have ordinal() method, which returns the numerical position of each enum\n" +
                        "constant in it type",
                "look example below with deriving an associated value via ordinal",
                "that method will break if the enums will be reordered",
                "never derive a value associated with an enum from its ordinal; store it in an instance field instead",
                "look at refactored Ensemble below",
                """
                        enum spec says: 'most programmers will have no use for this method. It is designed for use by
                        general-purpose enum-based data structures such as EnumSet and EnumMap'. So if you are not
                        on something like this - avoid ordinal()"""
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    // Abuse of ordinal to derive an associated value - DON'T DO THIS
    public enum Ensemble {
        SOLO, DUET, TRIO, QUARTET, QUINTET,
        SEXTET, SEPTET, OCTET, NONET, DECTET;
        public int numberOfMusicians() { return ordinal() + 1; }
    }

    public enum EnsembleRefactored {
        SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
        SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
        NONET(9), DECTET(10), TRIPLE_QUARTET(12);
        private final int numberOfMusicians;
        EnsembleRefactored(int size) { this.numberOfMusicians = size; }
        public int numberOfMusicians() { return numberOfMusicians; }
    }
}
