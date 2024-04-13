package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.math.BigDecimal;
import java.util.List;

@AutoService(Item.class)
public class AvoidFloatAndDoubleIfExactAnswersRequired implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Avoid float and double if exact answers required";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "the float and double types are designed for scientific and engineering calculations",
                "they perform binary floating-point arithmetic",
                "the float and double types are particularly ill-suited for monetary calculations, because it is " +
                        "impossible to represent 0.1 (or any other negative power of ten)",
                "always use BigDecimal, int, ot long for monetary calculations"
        );
    }

    @Override
    public void runExamples() {
        System.out.println(1.03 - 0.42);
        System.out.println(1.00 - 9 * 0.10);

        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Change: $" + funds);

        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought2 = 0;
        BigDecimal funds2 = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS;
             funds2.compareTo(price) >= 0;
             price = price.add(TEN_CENTS)) {
            funds2 = funds2.subtract(price);
            itemsBought2++;
        }
        System.out.println(itemsBought2 + " items bought.");
        System.out.println("Money left over: $" + funds2);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
