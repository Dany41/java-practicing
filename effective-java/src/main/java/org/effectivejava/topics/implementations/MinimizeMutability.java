package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@AutoService(Item.class)
public class MinimizeMutability implements Item {
    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(String.class, Integer.class, BigInteger.class, BigDecimal.class);
    }

    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Minimize mutability";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "An immutable class - a class whose instances cannot be modified. " +
                        "All of the info contained in each instance is fixed for the lifetime of the object",
                """
                    Rules to make class immutable:
                        1) Don't provide methods that modify the object's state
                        2) Ensure that the class can't be extended
                        3) Make all fields final
                        4) Make all fields private
                        5) Ensure exclusive access to any mutable components
                    """,
                "Returning new Class instance in methods instead of modifying the existing one is known as functional approach (see Complex class below)",
                "Contrast to functional approach are imperative or procedural in which methods apply a procedure to their operand, causing its state to mutate",
                "With functional approach it is conventional to name methods with prepositions, for example 'plus' instead of 'add'",
                """
                    Cons:
                        1) Immutable objects are simple
                        2) Immutable objects are inherently thread-safe; they require no synchronization
                        3) Immutable objects can be shared freely between the threads
                    """,
                "But if we create new instances each time we have to care more about space: for example, " +
                        "encourage clients to reuse existing instances whenever possible (public static final constants, see in Complex class)," +
                        "or immutable class can provide static factories that cache frequently requested instances (see primitive classes or BigInteger)",
                "Nice example with cached Integer is in integerEquals example code below",
                ""
        );
    }

    @Override
    public void runExamples() {
        System.out.println("integerEquals(200, 300) = " + integerEquals(200, 300));
        System.out.println("integerEquals(200, 200) = " + integerEquals(200, 200));
        System.out.println("integerEquals(10, 10) = " + integerEquals(10, 10));
    }

    public static boolean integerEquals(Integer a, Integer b) {
        return a == b;
    }


    // TAKEN FROM A BOOK
    // Immutable complex number class
    private static final class Complex {
        private final double re;
        private final double im;

        public Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double realPart() {
            return re;
        }

        public double imaginaryPart() {
            return im;
        }

        public Complex plus(Complex c) {
            return new Complex(re + c.re, im + c.im);
        }

        public Complex minus(Complex c) {
            return new Complex(re - c.re, im - c.im);
        }

        public Complex times(Complex c) {
            return new Complex(re * c.re - im * c.im,
                    re * c.im + im * c.re);
        }

        public Complex dividedBy(Complex c) {
            double tmp = c.re * c.re + c.im * c.im;
            return new Complex((re * c.re + im * c.im) / tmp,
                    (im * c.re - re * c.im) / tmp);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Complex))
                return false;
            Complex c = (Complex) o;
            // See page 47 to find out why we use compare instead of ==
            return Double.compare(c.re, re) == 0
                    && Double.compare(c.im, im) == 0;
        }

        @Override
        public int hashCode() {
            return 31 * Double.hashCode(re) + Double.hashCode(im);
        }

        @Override
        public String toString() {
            return "(" + re + " + " + im + "i)";
        }

        public static final Complex ZERO = new Complex(0, 0);
        public static final Complex ONE = new Complex(1, 0);
        public static final Complex I = new Complex(0, 1);
    }
}
