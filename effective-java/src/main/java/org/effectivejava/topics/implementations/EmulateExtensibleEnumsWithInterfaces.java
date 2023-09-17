package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AutoService(Item.class)
public class EmulateExtensibleEnumsWithInterfaces implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Emulate extensible enums with interfaces";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "enums are designed in a way that prohibits extensions of enum by different enums",
                "each enum is implicitly is a subtype of Enum class",
                "there is an example of use case for having extensible enums - opcode (see below Operation interface)",
                "but using interfaces help to solve the extensibility problem, see ExtendedOperation",
                "so, now, in code, you only need to accept Operation interface, not specific enum and problem is solved",
                "look at example in runExamples() to check usage example; try to get through them",
                "the disadvantage of emulating extensibility via interfaces: implementation cannot be inherited from " +
                        "one enum type to another",
                """
                    summary: while you cannot write an extensible enum type, you can emulate it by writing an interface
                    to accompany a basic enum type that implements the interface; it allows clients to write their own
                    enums that implement the interface""",
                "",
                "",
                ""
        );
    }

    @Override
    public void runExamples() {
        test(ExtendedOperation.class, 1.0, 5.0);
        test(Arrays.asList(ExtendedOperation.values()), 1.0, 5.0);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                LinkOption.class // enum implements the CopyOption and OpenOption interfaces
        );
    }

    // Emulated extensible enum using an interface
    public interface Operation {
        double apply(double x, double y);
    }
    public enum BasicOperation implements Operation {
        PLUS("+") {
            public double apply(double x, double y) { return x + y; }
        },
        MINUS("-") {
            public double apply(double x, double y) { return x - y; }
        },
        TIMES("*") {
            public double apply(double x, double y) { return x * y; }
        },
        DIVIDE("/") {
            public double apply(double x, double y) { return x / y; }
        };
        private final String symbol;
        BasicOperation(String symbol) {
            this.symbol = symbol;
        }
        @Override public String toString() {
            return symbol;
        }
    }

    // Emulated extension enum
    public enum ExtendedOperation implements Operation {
        EXP("^") {
            public double apply(double x, double y) {
                return Math.pow(x, y);
            }
        },
        REMAINDER("%") {
            public double apply(double x, double y) {
                return x % y;
            }
        };
        private final String symbol;
        ExtendedOperation(String symbol) {
            this.symbol = symbol;
        }
        @Override public String toString() {
            return symbol;
        }
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants())
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }

    private static void test(Collection<? extends Operation> opSet,
                             double x, double y) {
        for (Operation op : opSet)
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
