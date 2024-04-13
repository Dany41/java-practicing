package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseVarargsJudiciously implements Item {

    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Use varargs judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "varargs methods are formally known as variable arity method - accept zero or more arguments of a " +
                    "specified type",
            "the varargs facility works by first creating an array whose size is the number of arguments passed at the " +
                    "call site, then putting the argument values into the array, and passing the array to the method",
            "look at min method below and think why it is bad, then - refactor it",
            "the array creation impacts the performance - so it usual to see lots of overloadings with 1, 2, 3, etc " +
                    "parameters and, only then, vararg overloading"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // The WRONG way to use varargs to pass one or more arguments!
    public static int min(int... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Too few arguments");
        int min = args[0];
        for (int i = 1; i < args.length; i++)
            if (args[i] < min)
                min = args[i];
        return min;
    }
}
