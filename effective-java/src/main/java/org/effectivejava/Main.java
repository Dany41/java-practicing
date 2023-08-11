package org.effectivejava;

import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;
import org.effectivejava.topics.helpers.ItemUtils;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ServiceLoader;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        ServiceLoader.load(Item.class).stream()
                .map(ServiceLoader.Provider::get)
                .filter(impl -> impl.getChapter().equals(Chapter.CHAPTER_4))
                .forEach(ItemUtils::printItemDetails);
    }
}