package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;
import org.effectivejava.topics.helpers.ItemUtils;

import java.util.Arrays;
import java.util.List;

@AutoService(Item.class)
public class AvoidCreatingUnnecessaryObjects implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Avoid creating unnecessary objects";
    }

    @Override
    public List<String> getBulletPoints() {
        return Arrays.asList(
                "extreme example: String s = new String(\"sssss\")",
                "use factory methods or instances to get objects, they are not forced to create new instance each time they called as constructors",
                "methods `matches` on strings create instance of Pattern class each time, it is expensive,\n\t" +
                        "so, it is better to cache one instance of Pattern explicitly"
        );
    }

    @Override
    public void runExamples() {
        ItemUtils.measurePerformance("Measuring the code performance with autoboxing", () -> {
            Long sum = 0L;
            for (int i = 0; i < 1; i++) {
                sum += i;
            }
        });
        System.out.println("\nUsing Long object instead of primitive type will cause into creating Integer.MAX_VALUE Long objects\n");
        ItemUtils.measurePerformance("Measuring the code performance without autoboxing", () -> {
            long sum = 0L;
            for (int i = 0; i < 1; i++) {
                sum += i;
            }
        });
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Boolean.class
        );
    }
}
