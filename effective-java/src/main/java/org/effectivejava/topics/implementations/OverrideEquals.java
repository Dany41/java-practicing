package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AutoService(Item.class)
public class OverrideEquals implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_3;
    }

    @Override
    public String getTheme() {
        return "Obey the general contract when overriding equals";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                    Conditions to override equals method:
                    \ta) each instance of the class is inherently unique
                    \tb) there is no need for the class to provide a 'logical equality' test
                    \tc) a superclass has already overridden equals, and the superclass behavior is appropriate for this class, ex:
                    \t\t1 AbstractMap
                    \t\t2 AbstractSet
                    \t\t3 AbstractList
                    \td) the class is private or package-private, and you are certain the its equals method will never be invoked
                    """,
                """
                    Overridden equals method must follow the next rules:
                    \ta) reflexive: x.equals(x) == true
                    \tb) symmetric: x.equals(y) == y.equals(x)
                    \tc) transitive: x.equals(y), y.equals(z) ==> x.equals(z)
                    \td) consistent
                    \te) x.equals(null) == false
                    """,
                "once you've violated the equals contract, you simply don't know how other objects will behave when " +
                        "confronted with your object",
                "there is no way to extend an instantiable class and add a value component while preserving the equals contract" +
                        "(only if you want to forget about abstraction benefits)",
                "java.sql.Timestamp and java.util.Date classes is an example of violated equals contract",
                "instanceof does check for nullity, so there is no need to check on null explicitly (obj == null)",
                "@Overrides annotation helps you to understand if you really override method in the compile time",
                "if there are a lot of fields to check, try to use derived fields first to filter out not equals objects",
                "for double and float primitives use Double.compare and Float.compare methods respectively"

        );
    }

    @Override
    public void runExamples() {
        System.out.println("Checking what happens if have no equals overridden...");
        List<SimpleOne> simpleOnes = List.of(new SimpleOne(1), new SimpleOne(2), new SimpleOne(1), new SimpleOne(1));
        System.out.println(simpleOnes.stream().distinct().collect(Collectors.toList()));

        System.out.println();
        System.out.println("Broken reflexivity:");
        List<ReflexivityViolated> reflexivityViolatedList = new ArrayList<>();
        ReflexivityViolated reflexivityViolatedObject = new ReflexivityViolated();
        reflexivityViolatedList.add(reflexivityViolatedObject);
        System.out.println(reflexivityViolatedList.contains(reflexivityViolatedObject));

        System.out.println();
        System.out.println("Broken symmetry:");
        CaseInsensitiveString_SymmetryViolated cis = new CaseInsensitiveString_SymmetryViolated("Polish");
        String s = "polish";
        System.out.println("Does cis equals s ?: " + cis.equals(s));
        System.out.println("Does s equals cis ?: " + s.equals(cis));

        System.out.println();
        System.out.println("Below example can produce different result with different JDKs because we violated equals contract");
        List<CaseInsensitiveString_SymmetryViolated> list = new ArrayList<>();
        list.add(cis);
        System.out.println("Does 'cis' var is in the 'list' list? - " + list.contains(cis));

        System.out.println();
        System.out.println("Broken transitivity:");
        ColorPoint_TransitivityViolated p1 = new ColorPoint_TransitivityViolated(1, 2, Color.RED);
        Point_TransitivityViolated p2 = new Point_TransitivityViolated(1, 2);
        ColorPoint_TransitivityViolated p3 = new ColorPoint_TransitivityViolated(1, 2, Color.BLUE);
        System.out.println("p1.equals(p2) ?: " + p1.equals(p2));
        System.out.println("p2.equals(p3) ?: " + p2.equals(p3));
        System.out.println("p1.equals(p3) ?: " + p1.equals(p3));

        // cause SOE
//        ColorPoint_TransitivityViolated colorPoint = new ColorPoint_TransitivityViolated(1, 2, Color.BLACK);
//        SmellPoint_TransitivityViolated_SOE smellPoint = new SmellPoint_TransitivityViolated_SOE(1, 2, Color.BLACK);
//        System.out.println(colorPoint.equals(smellPoint));

        System.out.println();
        System.out.println("Try to hack and hold equals contract");
        System.out.println(onUnitCircle(new CounterPoint(0, 1)));


    }

    @Getter
    @AllArgsConstructor
    static class SimpleOne {
        private int i;

        @Override
        public String toString() {
            return "SimpleOne{" +
                    "i=" + i +
                    '}';
        }
    }

    static class ReflexivityViolated {
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    static class CaseInsensitiveString_SymmetryViolated {
        private final String s;

        public CaseInsensitiveString_SymmetryViolated(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CaseInsensitiveString_SymmetryViolated)
                return s.equalsIgnoreCase(((CaseInsensitiveString_SymmetryViolated) obj).s);
            if (obj instanceof String)
                return s.equalsIgnoreCase((String) obj);
            return false;
        }
    }

    static class Point_TransitivityViolated {
        private final int x;
        private final int y;

        public Point_TransitivityViolated(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point_TransitivityViolated))
                return false;
            Point_TransitivityViolated p = (Point_TransitivityViolated) obj;
            return p.x == x && p.y == y;
        }
    }

    static class ColorPoint_TransitivityViolated extends Point_TransitivityViolated {
        private final Color color;

        public ColorPoint_TransitivityViolated(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point_TransitivityViolated))
                return false;

            if (!(obj instanceof ColorPoint_TransitivityViolated))
                return obj.equals(this);

            return super.equals(obj) && ((ColorPoint_TransitivityViolated) obj).color == color;
        }
    }

    static class SmellPoint_TransitivityViolated_SOE extends Point_TransitivityViolated {
        private final Color color;

        public SmellPoint_TransitivityViolated_SOE(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point_TransitivityViolated))
                return false;

            if (!(obj instanceof SmellPoint_TransitivityViolated_SOE))
                return obj.equals(this);

            return super.equals(obj) && ((SmellPoint_TransitivityViolated_SOE) obj).color == color;
        }
    }

    static class Point {
        private final int x;
        private final int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass())
                return false;
            Point p = (Point) obj;
            return p.x == x && p.y == y;
        }
    }

    private static final Set<Point> unitCircle = Set.of(
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1));

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }

    static class CounterPoint extends Point {
        private static final AtomicInteger counter = new AtomicInteger();

        public CounterPoint(int x, int y) {
            super(x, y);
            counter.incrementAndGet();
        }

        public static int numberCreated() {
            return counter.get();
        }
    }

    // composition over inheritance pattern
    public class ColorPoint {
        private final Point point;
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            point = new Point(x, y);
            this.color = Objects.requireNonNull(color);
        }

        public Point asPoint() {
            return point;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ColorPoint))
                return false;
            ColorPoint cp = (ColorPoint) obj;
            return cp.point.equals(point) && cp.color.equals(color);
        }
    }

}
