package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@AutoService(Item.class)
public class UseEnumMapInsteadOfOrdinalIndexing implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Use EnumMap instead of ordinal indexing";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                    Example of usage of ordinal indexing in Map/Set is below, problems:
                        requires unchecked cast;
                        array does not know what its index represents - need to label the output manually
                        ints don't provide the type safety of enums, if you use wrong value - the program will silently
                            do the wrong thing or throw an ArrayIndexOutOfBoundsException""",
                "There is EnumMap created for such purposes (next example)",
                "Stream based implementation is more effective as it will create Map only in case there is any elements" +
                        "relate to the specific value of enum (for example if there is no annual plant -> size will be 2)",
                "One more example of hot not to do and solution see with enum Phase example below"
        );
    }

    @Override
    public void runExamples() {
        Set<Plant> garden = Set.of(
                new Plant("Tree", Plant.LifeCycle.PERENNIAL),
                new Plant("Flower", Plant.LifeCycle.ANNUAL),
                new Plant("Bamboo", Plant.LifeCycle.BIENNIAL),
                new Plant("Mushroom", Plant.LifeCycle.ANNUAL)
        );
        // Using ordinal() to index into an array - DON'T DO THIS!
        Set<Plant>[] plantsByLifeCycle =
                (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++)
            plantsByLifeCycle[i] = new HashSet<>();
        for (Plant p : garden)
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        // Print the results
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n",
                    Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }

        System.out.println();

        // Using an EnumMap to associate data with an enum **CAN BE EXERCISE**
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycleEM = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycleEM.put(lc, new HashSet<>());
        }
        for (Plant p : garden) {
            plantsByLifeCycleEM.get(p.lifeCycle).add(p);
        }
        System.out.println(plantsByLifeCycleEM);

        System.out.println();

        // Or, the same but with streams
        garden.stream()
                .collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), toSet()))
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    private static class Plant {
        enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }
        final String name;
        final LifeCycle lifeCycle;

        Plant(String name, LifeCycle lifeCycle) {
            this.name = name;
            this.lifeCycle = lifeCycle;
        }
        @Override public String toString() {
            return name;
        }
    }

    // Using ordinal() to index array of arrays - DON'T DO THIS!
    private enum Phase {
        SOLID, LIQUID, GAS;
        public enum Transition {
            MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
            // Rows indexed by from-ordinal, cols by to-ordinal
            private static final Transition[][] TRANSITIONS = {
                    { null, MELT, SUBLIME },
                    { FREEZE, null, BOIL },
                    { DEPOSIT, CONDENSE, null }
            };
            // Returns the phase transition from one phase to another
            public static Transition from(Phase from, Phase to) {
                return TRANSITIONS[from.ordinal()][to.ordinal()];
            }
        }
    }

    // Using a nested EnumMap to associate data with enum pairs
    public enum PhaseBetter {
        SOLID, LIQUID, GAS;
        public enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
            BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
            private final PhaseBetter from;
            private final PhaseBetter to;
            Transition(PhaseBetter from, PhaseBetter to) {
                this.from = from;
                this.to = to;
            }
            // Initialize the phase transition map
            private static final Map<PhaseBetter, Map<PhaseBetter, Transition>>
                    m = Stream.of(values()).collect(groupingBy(t -> t.from,
                    () -> new EnumMap<>(PhaseBetter.class),
                    toMap(t -> t.to, t -> t,
                            (x, y) -> y, () -> new EnumMap<>(PhaseBetter.class))));
            public static Transition from(PhaseBetter from, PhaseBetter to) {
                return m.get(from).get(to);
            }
        }
    }
}
