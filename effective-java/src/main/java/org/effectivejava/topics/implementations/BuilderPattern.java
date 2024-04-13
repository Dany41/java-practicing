package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;
import org.effectivejava.topics.pojos.Calzone;
import org.effectivejava.topics.pojos.NyPizza;
import org.effectivejava.topics.pojos.Pizza;

import java.util.List;

import static org.effectivejava.topics.pojos.NyPizza.Size.SMALL;

@AutoService(Item.class)
public class BuilderPattern implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Consider a builder when faced with many constructor parameters";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "apply when there are many arguments",
                "can be suited for class hierarchies"
        );
    }

    @Override
    public void runExamples() {
        NyPizza pizza = new NyPizza.NyBuilder(SMALL)
                .addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM).sauceInside().build();

        System.out.printf("""
                        Created via theirs own builders:
                        nyPizza: %s
                        calzone: %s
                        """,
                pizza.toString(),
                calzone.toString());
    }


}
