package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Arrays;
import java.util.List;

@AutoService(Item.class)
public class SingletonProperty implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Enforce the singleton property with a private constructor or an enum type";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "single-element enum type is often the best way to implement a singleton"
        );
    }

    @Override
    public void runExamples() {
        System.out.println("'singleton' ELVIS: " + Elvis.INSTANCE);
        System.out.println("New instances :)) : ");

        Arrays.stream(Elvis.class.getDeclaredConstructors())
                .map(constructor -> {
                    constructor.setAccessible(true);
                    try {
                        return constructor.newInstance();
                    } catch (Exception e) {
                        System.out.println("oops, the error occurred while hacking 'singleton': " + e.getMessage());
                    }
                    return Elvis.INSTANCE;
                })
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Antonus has the same problem even if we create 'singleton' via factory method");

        System.out.println("'singleton' ANTONUS: " + Antonus.getInstance());
        System.out.println("New instances :)) : ");

        Arrays.stream(Antonus.class.getDeclaredConstructors())
                .map(constructor -> {
                    constructor.setAccessible(true);
                    try {
                        return constructor.newInstance();
                    } catch (Exception e) {
                        System.out.println("oops, the error occurred while hacking 'singleton': " + e.getMessage());
                    }
                    return Antonus.getInstance();
                })
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Ouu 'singleton' is created via 'enum': " + Ouu.INSTANCE);
        System.out.println("Ouu 'singleton' is created via 'enum': " + Ouu.SECOND);

        Arrays.stream(Ouu.class.getDeclaredConstructors())
                .map(constructor -> {
                    constructor.setAccessible(true);
                    try {
                        return constructor.newInstance();
                    } catch (Exception e) {
                        System.out.println("oops, the error occurred while hacking 'singleton': " + e.getMessage());
                    }
                    return Ouu.INSTANCE;
                })
                .forEach(System.out::println);
    }
}

// Singleton with public final field
class Elvis {
    private final int innerState = 0;
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}

    public void leaveTheBuilding() {
        System.out.println(this);
    }
}

// Singleton with static factory
class Antonus {
    private static final Antonus INSTANCE = new Antonus();
    private Antonus() {}
    public static Antonus getInstance() { return INSTANCE; }
    public void leaveTheBuilding() {
        System.out.println(this);
    }
}

enum Ouu {
    INSTANCE(1),SECOND(2);

    private int someNumber;

    Ouu(int i) {
        this.someNumber = i;
    }

    static {
        System.out.println("AHAHAHAHHAHAH");
    }

    public void leaveTheBuilding() {
        System.out.println(this);
    }
}
