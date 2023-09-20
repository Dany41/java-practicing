package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@AutoService(Item.class)
public class PreferSideEffectFreeFunctionsInStreams implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Prefer side-effect-free functions in streams";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "to obtain expressiveness, speed, sometime parallelization while using Stream API, you should " +
                    "also stick to its paradigm, which is based in functional programming",
            "most important part of the paradigm is: referential transparency: pure functions",
            "an unappropriated usage is below with fixing", // exercise - refactor
            "the forEach operation should be used only to report the result of a stream computation, not to perform " +
                    "the computation",
            "it is customary and wise to statically import all members of Collectors because it makes stream pipelines " +
                    "more readable",
            "there is never a reason to say collect(counting())"
        );
    }

    @Override
    public void runExamples() {
        String source = "a b c d s e sa f g a";

        // Uses the streams API but not the paradigm--Don't do this!
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner(source).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }




        // Proper use of streams to initialize a frequency table
        Map<String, Long> freqOk;
        try (Stream<String> words = new Scanner(source).tokens()) {
            freqOk = words
                    .collect(groupingBy(String::toLowerCase, counting()));
        }
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
