package org.effectivejava.topics.helpers;

import org.effectivejava.topics.abstractions.Item;

public class ItemUtils {

    public static void printItemDetails(Item item) {
        System.out.printf("""
                
                
                ==========================-%s-================================
                Theme: %s
                =====================================================================
                """,
                item.getChapter(),
                item.getTheme());
        System.out.println("----> Main points from the chapter");
        item.getBulletPoints().stream()
                .map(ItemUtils::appendHyphen)
                .forEach(System.out::println);

        System.out.println("----> EXAMPLES");
        System.out.println();
        item.runExamples();
    }

    private static String appendHyphen(String str) {
        return " - " + str;
    }


    public static void measurePerformance(String message, Runnable runnable) {
        System.out.println(message);
        measurePerformance(runnable);
    }

    private static void measurePerformance(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        System.out.println("Executed in " + (System.currentTimeMillis() - start) + " mills");
    }
}
