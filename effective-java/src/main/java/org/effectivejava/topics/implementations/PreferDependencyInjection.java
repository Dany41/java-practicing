package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@AutoService(Item.class)
public class PreferDependencyInjection implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Prefer dependency injection to hardwiring resources";
    }

    @Override
    public List<String> getBulletPoints() {
        return Arrays.asList(
                "static utility classes and singletons are inappropriate for classes whose behavior is parametrized by an underlying resource",
                "simple answer (pattern) - pass the resource into the constructor when creating a new instance"
        );
    }

    @Override
    public void runExamples() {
    }

    // Bad approach - inflexible & untestable (you cannot inject the dictionary you want, it is hardcoded)
    // Another way - make this class singleton, but it doesn't solve the mentioned problems
    // These two approaches assume that there is only one dictionary, but in reality each language has its own
    // Also, special dictionaries may be used for special vocabularies, or for testing reasons we may want to have
    // another dictionary
    // If we make dictionary non-final and add setter/getter - it would be awkward, unworkable in concurrent setting
    public static class SpellChecker {
        private static final List<String> dictionary = List.of("word", "слово");

        private SpellChecker() {}

        public boolean isValid(String word) {
            return dictionary.stream().anyMatch(w -> w.equals(word));
        }

        public List<String> suggestions(String typo) {
            return Arrays.asList(
                    typo + "ish",
                    typo + "ouch"
            );
        }
    }

    // Correct approach is using dependency injection straightly in the constructor, or by providing Factory object
    // (usually implements Supplier<?> interface)
    public static class CorrectSpellChecker {
        private final List<String> dictionary;

        public CorrectSpellChecker(List<String> dictionary) {
            this.dictionary = Objects.requireNonNull(dictionary);
        }

        public CorrectSpellChecker(Supplier<List<String>> dictionaryFactory) {
            this.dictionary = Objects.requireNonNull(dictionaryFactory.get());
        }

        public boolean isValid(String word) {
            return dictionary.stream().anyMatch(w -> w.equals(word));
        }


    }
}
