package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static org.effectivejava.topics.helpers.ItemUtils.measurePerformance;

@AutoService(Item.class)
public class ImplementingComparable implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_3;
    }

    @Override
    public String getTheme() {
        return "Consider implementing Comparable";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Comparable provides compareTo method. Which executes the same role as equals + permits order comparisons",
                "By implementing comparable, a class indicates that its instances have a natural ordering",
                "Enum and value classes implements Comparable, but not Record",
                """
                        compareTo's contract (sgn() means signum function from Math which returns -1,0,1 for negative numbers, zero, and positive numbers respectively):
                        \ta) returns s a negative integer, zero, or a positive integer as this object is less than, equal to,
                                or greater than the specified object
                        \tb) Throws ClassCastException if the specified object’s type prevents it from being compared to this object
                        \tc) sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y
                                (This implies that x.compareTo(y) must throw an exception if and only if y.compareTo(x) throws an exception.)
                        \td) Transitivity: : (x.compareTo(y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0.
                        \te) x.compareTo(y) == 0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z
                        \tf) STRONGLY RECOMMENDED BUT NOT REQUIRED
                                (x.compareTo(y) == 0) == (x.equals(y)).
                               Good tone: if class implements Comparable, but violates above - it must be clearly indicated in doc
                        """,
                "Class that violates the contract can break other classes that depend on comparison: TreeSet, TreeMap, Collections, Arrays, etc",
                "The same Discussions from equals applied here to the case when compareTo should return 0",
                "Collection, Map, Set interfaces' contracts are defined in terms of equals, but the mentioned above implementations\n" +
                        "use equality with help of compareTo. No catastrophe if equals and compareTo are inconsistent, just FYI",
                "Example of class that have inconsistent equals and compareTo methods: BigDecimal",
                "Comparable interface is parametrized, so there is no need in casting (unlike in the equals())\n" +
                        "it prevent from invocation with wrong time at compile time, which is much better than in equals() case",
                "Using '<' and '>' is not recommended because of verbose and error-prone. Use boxing equivalent instead",
                "If there are more than 1 significant fields. In implementation compare from most significant to least one",
                """
                        Example of implemented compareTo:
                            public int compareTo(PhoneNumber pn) {
                                int result = Short.compare(areaCode, pn.areaCode);
                                if (result == 0) {
                                    result = Short.compare(prefix, pn.prefix);
                                if (result == 0)
                                    result = Short.compare(lineNum, pn.lineNum);
                                }
                                return result;
                            }

                        """,
                "In Java 8 there are lots of comparator construction methods were added by providing Comparator Function Interface\n" +
                        "but it decreases the performance of a program",
                "Comparator have lots of static methods, for example comparingInt, thenComparingInt, etc for long and double primitive types\n" +
                        "also 2 overloading for comparing and three for themComparing methods",
                "Whenever you create value class that has a sensible ordering, you should implement Comparable"
        );
    }

    @Override
    public void runExamples() {
        System.out.println("Example of inconsistency between equals and compareTo methods in BigDecimal:");

        Set<BigDecimal> bigDecimalSet = new HashSet<>();
        bigDecimalSet.add(new BigDecimal("1.0"));
        bigDecimalSet.add(new BigDecimal("1.00"));
        System.out.println("Number of elements in bigDecimalSet after adding 1.0 and 1.00 is: " + bigDecimalSet.size());


        System.out.println();
        System.out.println("Question: Why it is better to use BigDecimal(String) rather than BigDecimal(double) constructor?");

        System.out.println();
        System.out.println("There are two elements because the added elements are unequals by equals() method");

        System.out.println();
        System.out.println("Lets repeat the same for TreeSet()");

        Set<BigDecimal> bigDecimalTreeSet = new TreeSet<>();
        bigDecimalTreeSet.add(new BigDecimal("1.0"));
        bigDecimalTreeSet.add(new BigDecimal("1.00"));
        System.out.println("Number of elements in bigDecimalSet after adding 1.0 and 1.00 is: " + bigDecimalTreeSet.size());

        System.out.println();
        System.out.println("There is only one element as TreeSet uses compareTo method while adding elements to a set");

        System.out.println();
        measurePerformance("Measuring performance without Comparator", () -> {
            for (int i = 0; i < 100_000_000; i++) {
                new PhoneNumber(i, i + 1, i + 2).compareTo(new PhoneNumber(i, i + 1, i + 3));
            }
        });

        System.out.println();
        measurePerformance("Measuring performance with Comparator", () -> {
            for (int i = 0; i < 100_000_000; i++) {
                new PhoneNumber(i, i + 1, i + 2).compareTo2(new PhoneNumber(i, i + 1, i + 3));
            }
        });

    }

    public record Person(String name, int age) {}
    enum Numbers {
        ONE, TWO, THREE, FOUR
    }

    public static class PhoneNumber {

        private final short areaCode, prefix, lineNum;
        public PhoneNumber(int areaCode, int prefix, int lineNum) {
            this.areaCode = rangeCheck(areaCode, 999999999, "area code");
            this.prefix = rangeCheck(prefix, 999999999, "prefix");
            this.lineNum = rangeCheck(lineNum, 999999999, "line num");
        }
        private static short rangeCheck(int val, int max, String arg) {
            if (val < 0 || val > max)
                throw new IllegalArgumentException(arg + ": " + val);
            return (short) val;
        }
        @Override public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof PhoneNumber))
                return false;
            PhoneNumber pn = (PhoneNumber)o;
            return pn.lineNum == lineNum && pn.prefix == prefix
                    && pn.areaCode == areaCode;
        }

        // Comparable with comparator construction methods
        private static final Comparator<PhoneNumber> COMPARATOR =
                comparingInt((PhoneNumber pn) -> pn.areaCode)
                        .thenComparingInt(pn -> pn.prefix)
                        .thenComparingInt(pn -> pn.lineNum);
        public int compareTo2(PhoneNumber pn) {
            return COMPARATOR.compare(this, pn);
        }

        public int compareTo(PhoneNumber pn) {
            int result = Short.compare(areaCode, pn.areaCode);
            if (result == 0) {
                result = Short.compare(prefix, pn.prefix);
                if (result == 0)
                    result = Short.compare(lineNum, pn.lineNum);
            }
            return result;
        }
    }
}
