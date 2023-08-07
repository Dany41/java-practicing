package org.practicalunittesting;

import java.util.Collection;
import java.util.HashSet;

public class Fridge {
    private Collection<String> food = new HashSet<>();
    public boolean put(String item) {
        return food.add(item);
    }
    public boolean contains(String item) {
        return food.contains(item);
    }
    public void take(String item) throws NoSuchItemException {
        boolean result = food.remove(item);
        if (!result) {
            throw new NoSuchItemException(item
                    + " not found in the fridge");
        }
    }
}
