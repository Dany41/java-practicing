package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@AutoService(Item.class)
public class UseEnumsInsteadOfIntConstants implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Use enums instead of int constants";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "enumerated type is a type whose legal values consist of a fixed set of constants",
                "before enums were added to the java - a common pattern for representing enumerated types was int enum\n" +
                        "pattern (see below)",
                "using this pattern has disadvantages: you cannot differentiate type; compiler will not complain even if\n" +
                        "you do arithmetic operations with them; it is tricky to print the name of such enum",
                "the basic idea behind Java's enum types: they are classes that export one instance for each enumeration\n" +
                        "constant via a public static final field",
                "enum types are instance-controlled",
                "a generalization of singletons, which are essentially single-element enums",
                "enums provide compile time safety",
                "enums provide high-quality implementations of all the Object methods, implement Comparable and Serializable",
                "you can add fields and methods to them",
                "enums are by nature immutable, so all fields should be final",
                "if the element is deleted from the enum - if the client code didn't use it - it will work fine, no need\n" +
                        "to recompile",
                "methods that switches on enum values are anti-pattern if they located in the enum (see example in\n" +
                        "Operation below); the better use showed in BetterOperation",
                "if you override toString method -> consider writing method fromString() to return Enum value from a\n" +
                        "given string (example see in EvenBetterOperation)",
                """
                enum constructors are not permitted to access the enum's static fields, with the exception of constant
                variables; it is needed because static fields have not yet been initialized when enum
                constructors run; the special case for this restriction is that enum constants cannot access one another
                from their constructors""",
                "look at PayrollDay and try to refactor it in order to force developer to provide logic when new\n" +
                        "constant is added",
                "use enums any time you need a set of constants whose members are known at compile time",
                "it is not necessary that the set of constants in an enum type stay fixed for all time; the enum feature\n" +
                        "was designed to allow for binary compatible evolution of enum types"
        );
    }

    @Override
    public void runExamples() {
        double earthWeight = 185;
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values())
            System.out.printf("Weight on %s is %f%n",
                    p, p.surfaceWeight(mass));

        double x = Double.parseDouble("10");
        double y = Double.parseDouble("12");
        for (EvenBetterOperation op : EvenBetterOperation.values())
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    // The int enum pattern - severely deficient!
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
    public enum Orange { NAVEL, TEMPLE, BLOOD }

    // Enum type with data and behavior
    public enum Planet {
        MERCURY(3.302e+23, 2.439e6),
        VENUS (4.869e+24, 6.052e6),
        EARTH (5.975e+24, 6.378e6),
        MARS (6.419e+23, 3.393e6),
        JUPITER(1.899e+27, 7.149e7),
        SATURN (5.685e+26, 6.027e7),
        URANUS (8.683e+25, 2.556e7),
        NEPTUNE(1.024e+26, 2.477e7);
        private final double mass; // In kilograms
        private final double radius; // In meters
        private final double surfaceGravity; // In m / s^2
        // Universal gravitational constant in m^3 / kg s^2
        private static final double G = 6.67300E-11;
        // Constructor
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
            surfaceGravity = G * mass / (radius * radius);
        }
        public double mass() { return mass; }
        public double radius() { return radius; }
        public double surfaceGravity() { return surfaceGravity; }
        public double surfaceWeight(double mass) {
            return mass * surfaceGravity; // F = ma
        }
    }

    // Enum type that switches on its own value - questionable
    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;
        // Do the arithmetic operation represented by this constant
        public double apply(double x, double y) {
            return switch (this) {
                case PLUS -> x + y;
                case MINUS -> x - y;
                case TIMES -> x * y;
                case DIVIDE -> x / y;
            };
        }
    }

    // Enum type with constant-specific method implementations
    public enum BetterOperation {
        PLUS {public double apply(double x, double y){return x + y;}},
        MINUS {public double apply(double x, double y){return x - y;}},
        TIMES {public double apply(double x, double y){return x * y;}},
        DIVIDE{public double apply(double x, double y){return x / y;}};
        public abstract double apply(double x, double y);
    }

    // Enum type with constant-specific class bodies and data
    public enum EvenBetterOperation {
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
        EvenBetterOperation(String symbol) { this.symbol = symbol; }
        @Override public String toString() { return symbol; }
        public abstract double apply(double x, double y);

        // Implementing a fromString method on an enum type
        private static final Map<String, EvenBetterOperation> stringToEnum =
                Stream.of(values()).collect(
                        toMap(Object::toString, e -> e));
        // Returns Operation for string, if any
        public static Optional<EvenBetterOperation> fromString(String symbol) {
            return Optional.ofNullable(stringToEnum.get(symbol));
        }
    }

    // Enum that switches on its value to share code - questionable
    enum PayrollDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
        SATURDAY, SUNDAY;
        private static final int MINS_PER_SHIFT = 8 * 60;
        int pay(int minutesWorked, int payRate) {
            int basePay = minutesWorked * payRate;
            int overtimePay = switch (this) {
                case SATURDAY, SUNDAY -> // Weekend
                        basePay / 2;
                default -> // Weekday
                        minutesWorked <= MINS_PER_SHIFT ?
                                0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
            };
            return basePay + overtimePay;
        }
    }

    // The strategy enum pattern
    enum StrategyPayrollDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
        SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
        private final PayType payType;
        StrategyPayrollDay(PayType payType) { this.payType = payType; }
        StrategyPayrollDay() { this(PayType.WEEKDAY); } // Default
        int pay(int minutesWorked, int payRate) {
            return payType.pay(minutesWorked, payRate);
        }
        // The strategy enum type
        private enum PayType {
            WEEKDAY {
                int overtimePay(int minsWorked, int payRate) {
                    return minsWorked <= MINS_PER_SHIFT ? 0 :
                            (minsWorked - MINS_PER_SHIFT) * payRate / 2;
                }
            },
            WEEKEND {
                int overtimePay(int minsWorked, int payRate) {
                    return minsWorked * payRate / 2;
                }
            };
            abstract int overtimePay(int mins, int payRate);
            private static final int MINS_PER_SHIFT = 8 * 60;
            int pay(int minsWorked, int payRate) {
                int basePay = minsWorked * payRate;
                return basePay + overtimePay(minsWorked, payRate);
            }
        }
    }
}
