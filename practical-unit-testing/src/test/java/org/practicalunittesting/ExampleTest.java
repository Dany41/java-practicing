package org.practicalunittesting;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {


    @Test
    void testMethod() {

        assertThat("some string").isNotEmpty();
        assertThat(1).isLessThan(2);
        assertThat(1).isGreaterThanOrEqualTo(0);
        assertThat(true).isTrue();
        assertThat(Arrays.asList(1, 2, 3)).contains(1);

        assertThat("some string")
                .isNotEmpty()
                .hasSize(11)
                .matches("some.*");

    }

    @Test
    void learnAssertJ() {

        double doubleVar = 2.5;
        assertThat(doubleVar).isCloseTo(1.1, Offset.offset(2.0));
        assertThat(doubleVar).isCloseTo(3.1, Offset.offset(2.0));

        String stringVar = "string";
        assertThat(stringVar).asBase64Decoded().asBase64Encoded().isBase64();

        BigDecimal bigDecimalVar = BigDecimal.TEN;
        assertThat(bigDecimalVar).hasScaleOf(0);
        assertThat(bigDecimalVar).isNotEqualByComparingTo("5");

        List<Object> listVar = new ArrayList<>();
        assertThat(listVar).isNotOfAnyClassIn(HashMap.class, TreeMap.class, LinkedList.class);
        assertThat(listVar).containsExactlyElementsOf(Collections.emptyList());
        assertThat(listVar).doesNotHaveDuplicates();
        assertThat(listVar).hasSizeLessThan(10);

    }

}
