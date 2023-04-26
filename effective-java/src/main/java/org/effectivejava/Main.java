package org.effectivejava;

import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;
import org.effectivejava.topics.helpers.ItemUtils;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader.load(Item.class).stream()
                .map(ServiceLoader.Provider::get)
                .filter(impl -> impl.getChapter().equals(Chapter.CHAPTER_3))
                .forEach(ItemUtils::printItemDetails);
    }
}