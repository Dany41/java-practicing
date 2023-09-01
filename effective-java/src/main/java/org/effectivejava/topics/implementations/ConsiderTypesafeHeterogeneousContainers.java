package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.lang.reflect.AnnotatedElement;
import java.util.*;

@AutoService(Item.class)
public class ConsiderTypesafeHeterogeneousContainers implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_5;
    }

    @Override
    public String getTheme() {
        return "Consider typesafe heterogeneous containers";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "way described helps with data structures where it is needed arbitrary amount of type parameters",
                "when a class literal is passed among methods to communicate both compile-time and runtime type information\n" +
                        "it is called type token",
                "if you want to insert break line in printf() - you type '%n', not '\\n', for platform independence",
                "a Favorites instance below is typesafe: it will never return an Integer when you ask it for a String",
                "it is also heterogeneous: unlike ordinary map, all the key are of different types",
                "so Favorites is called - typesafe heterogeneous container",
                "as wildcard is nested in Favorites map instance - it allows to have different types - it is where\n" +
                        "heterogeneity comes from",
                "a Favorite is easy to corrupt by using raw Class object",
                "to achieve runtime safety we can cast an object in putFavorite method",
                "there are similar classes with the same trick in the standard library: CheckedList, CheckedSet, etc",
                "the second limitation of the Favorites class is that it cannot be used on a non-reifiable type "
        );
    }

    @Override
    public void runExamples() {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", favoriteString,
                favoriteInteger, favoriteClass.getName());
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(
                Collections.class, // checkedList, checkedSet, checkedMap
                AnnotatedElement.class // typesafe heterogeneous container
        );
    }

    // Typesafe heterogeneous container pattern - API
    private static class Favorites {
        private Map<Class<?>, Object> favorites = new HashMap<>();
        public <T> void putFavorite(Class<T> type, T instance) {
            favorites.put(Objects.requireNonNull(type), instance);
        }

        public <T> T getFavorite(Class<T> type) {
            return type.cast(favorites.get(type));
        }
    }
}
