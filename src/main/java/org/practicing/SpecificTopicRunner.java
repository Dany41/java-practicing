package org.practicing;

import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.ItemUtils;

import java.util.ServiceLoader;


public class SpecificTopicRunner {
    public static void main(String[] args) {
        if (args.length != 1) throw new RuntimeException("fuck");
        String patternToFind = args[0];
        ServiceLoader.load(Item.class).stream()
                .map(ServiceLoader.Provider::get)
                .filter(impl -> impl.getTheme().contains(patternToFind))
                .forEach(ItemUtils::printItemDetails);
    }
}