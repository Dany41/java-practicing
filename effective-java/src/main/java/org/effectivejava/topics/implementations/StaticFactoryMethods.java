package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import lombok.Getter;
import org.effectivejava.topics.helpers.EjChapter;
import org.effectivejava.serviceloader.Chosen;
import org.effectivejava.serviceloader.Implementation1;
import org.effectivejava.serviceloader.ServiceInterface;
import org.abstractions.Item;

import java.util.List;
import java.util.ServiceLoader;

@AutoService(Item.class)
public class StaticFactoryMethods implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Consider static factory methods instead of constructors";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "factory methods have custom names",
                "there is no requirement to create an object each time when factory method is called",
                "factory methods can return subtype of its return type",
                "class of the returned object can vary depending on parameters",
                "class of the returned object need not exist when the class containing the method is written",
                "classes without public constructors cannot be subclassed"
        );
    }

    @Override
    public void runExamples() {

        // supposed to be the same point
        Point pointShit1 = new Point(1.0, 1.0);
        Point pointShit2 = new Point(Math.sqrt(2.0), Math.PI / 4);

        // now are the same, but look ugly
        Point pointShitALittleBetter1 = new Point(1.0, 1.0, Point.PointType.CARTESIAN);
        Point pointShitALittleBetter2 = new Point(Math.sqrt(2.0), Math.PI / 4, Point.PointType.POLAR);

        // points of the healthy system
        Point healthyPoint1 = Point.newCartesianPoint(1.0, 1.0);
        Point healthyPoint2 = Point.newPolarPoint(Math.sqrt(2.0), Math.PI / 4);

        System.out.printf(
                """
                        pointShit1: %s
                        pointShit1: %s
                        \n
                        pointShitALittleBetter1: %s
                        pointShitALittleBetter2: %s
                        \n
                        healthyPoint1: %s
                        healthyPoint2: %s
                            """,
                pointShit1,
                pointShit2,
                pointShitALittleBetter1,
                pointShitALittleBetter2,
                healthyPoint1,
                healthyPoint2
        );

        // subclass can be created at runtime via reflection and pulled out via ServiceLoader
        ServiceLoader<ServiceInterface> loader = ServiceLoader.load(ServiceInterface.class);
        ServiceInterface serviceInterfaceImplementation = loader.stream()
                .filter(impl -> impl.type().isAnnotationPresent(Chosen.class))
                .map(ServiceLoader.Provider::get)
                .findFirst()
                .orElseGet(Implementation1::new);
        serviceInterfaceImplementation.test();
    }

    // if you have two ways to create object with the same contract - constructors can't help,
    // but you can create two static methods with appropriate name
    @Getter
    static private class Point {
        private double x;
        private double y;

        // to allow inheritance
        public Point() {
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        // DON'T DO THIS
        enum PointType {
            CARTESIAN, POLAR
        }

        public Point(double x, double y, PointType type) {
            switch (type) {
                case CARTESIAN -> {
                    this.x = x;
                    this.y = y;
                }
                case POLAR -> {
                    this.x = x * Math.sin(y);
                    this.y = x * Math.cos(y);
                }
            }
        }

        // INSTEAD DO THIS
        private Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double radius, double angle) {
            return new Point(radius * Math.sin(angle), radius * Math.cos(angle));
        }

        public static Point newEnhancedCartesianPoint(double x, double y, double z) {
            return (z == 0) ? new Point(x, y) : new EnhancedPoint(x, y, z);
        }

    }

    @Getter
    static private class EnhancedPoint extends Point {

        private double z;

        public EnhancedPoint(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }
    }


}
