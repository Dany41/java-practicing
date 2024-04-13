package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AutoService(Item.class)
public class PreferForEachLoopsToTraditionalForLoops implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Prefer for-each loops to traditional for loops";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "there is no performance penalty for using for-each loops, even for arrays",
                "look at a book for exercise - spotting a bug",
                """
                    there are three situations where you can't use for-each loop:
                        - destructive filtering
                        - transforming
                        - parallel iteration""",
                "for-each loop lets you iterate over any object that implements the Iterable interface",
                "for-each loop provides compelling advantages over the traditional for loop in clarity, flexibility, " +
                        "and bug prevention, with no performance penalty"
        );
    }

    public void remove(List<Character> chars) {
        char end = 'z';
        Predicate<Character> predicate = (var var) -> {
            char start = 'a'; return start <= var && var <= end; };
        // INSERT LINE HERE
//        end = '2';
    }

    @Override
    public void runExamples() {
        List<Integer> lists = List.of(1, 2, 3);
        for (Integer i :
                lists) {
            
        }

        BigInteger length = BigInteger.valueOf(3);
        for (BigInteger i = BigInteger.ZERO; i.compareTo(BigInteger.valueOf(3)) < 0; i = i.add(BigInteger.ONE)) {
            if (i.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                Supplier<BigInteger> supplier = () -> length; // A
                System.out.println(supplier.get()); // B
            } else {
                BigInteger j = i;
                Supplier<BigInteger> supplier = () -> j; // C
                System.out.println(supplier.get()); // D
            }
        }
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
