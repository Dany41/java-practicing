package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AutoService(Item.class)
public class ConsistentlyUseTheOverrideAnnotation implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Consistently use the Override annotation";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "to see the advantages of annotation @Override - consider Bigram class below and try to find what is wrong",
            "by annotating methods in Bigram - you can spot the problem; it will show if you really override method",
            "use the Override annotation on every method declaration that you believe to override a superclass declaration",
            "there is case when you don't need to: when your class is not abstract and you override abstract method",
            "the Override annotation may be used on method declarations that override declarations from interfaces",
            "so, compiler can protect you from many errors if you consistently use the Override annotation"
        );
    }

    @Override
    public void runExamples() {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new Bigram(ch, ch));
        System.out.println(s.size());
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Can you spot the bug?
    private static class Bigram {
        private final char first;
        private final char second;
        public Bigram(char first, char second) {
            this.first = first;
            this.second = second;
        }
        public boolean equals(Bigram b) {
            return b.first == first && b.second == second;
        }
        public int hashCode() {
            return 31 * first + second;
        }
    }
}
