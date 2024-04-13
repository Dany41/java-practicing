package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@AutoService(Item.class)
public class UseOverloadingJudiciously implements Item {

    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Use overloading judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "the choice of which overloading to invoke is made at compile time",
            "selection among overloaded methods is static, while selection among overridden methods is dynamic",
            "avoid confusing uses of overloading",
            "a safe, conservative policy is never to export two overloadings with the same number of parameters",
            "you can always give methods different names instead of overloading them",
            "look at confusing example with lists and set below",
            "do not overload method to take different functional in the same argument position",
            "-Xlint:overloads - make java compiler warn you about this sort of problematic overloadings",
            "you can violate the advise if methods will do exactly the same thing - see String#contentEquals"
        );
    }

    @Override
    public void runExamples() {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        for (Collection<?> c : collections)
            System.out.println(CollectionClassifier.classify(c));

        List<Wine> wineList = List.of(
                new Wine(), new SparklingWine(), new Champagne());
        for (Wine wine : wineList)
            System.out.println(wine.name());

        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(i);
        }
        System.out.println(set + " " + list);

//        new Thread(System.out::println).start();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit(System.out::println);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                ObjectOutputStream.class,   // write defined for every primitive
                ObjectInputStream.class,    // read defined for every primitive
                String.class                // contentEquals; violation - valueOf
        );
    }

    // Broken! - What does this program print?
    private static class CollectionClassifier {
        public static String classify(Set<?> s) {
            return "Set";
        }

        public static String classify(List<?> lst) {
            return "List";
        }

        public static String classify(Collection<?> c) {
            return "Unknown Collection";
        }
    }

    private static class Wine {
        String name() { return "wine"; }
    }
    private static class SparklingWine extends Wine {
        @Override String name() { return "sparkling wine"; }
    }
    private static class Champagne extends SparklingWine {
        @Override String name() { return "champagne"; }
    }
}
