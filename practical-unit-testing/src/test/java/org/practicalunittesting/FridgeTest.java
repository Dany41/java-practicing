package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FridgeTest {


//    @Test
//    void testFridge() {
//        Fridge fridge = new Fridge();
//        fridge.put("cheese");
//        assertEquals(true, fridge.contains("cheese"));
//        assertEquals(false, fridge.put("cheese"));
//        assertEquals(true, fridge.contains("cheese"));
//        assertEquals(false, fridge.contains("ham"));
//        fridge.put("ham");
//        assertEquals(true, fridge.contains("cheese"));
//        assertEquals(true, fridge.contains("ham"));
//        try {
//            fridge.take("sausage");
//            fail("There was no sausage in the fridge!");
//        } catch (NoSuchItemException e) {
//            // ok
//        }
//    }

//    @Test
//    void testPutTake() throws NoSuchItemException {
//        Fridge fridge = new Fridge();
//        List<String> food = new ArrayList<String>();
//        food.add("yogurt");
//        food.add("milk");
//        food.add("eggs");
//        for (String item : food) {
//            fridge.put(item);
//            assertEquals(true, fridge.contains(item));
//            fridge.take(item);
//            assertEquals(false, fridge.contains(item));
//        }
//        for (String item : food) {
//            try {
//                fridge.take(item);
//                fail("there was no " + item + " in the fridge");
//            } catch (NoSuchItemException e) {
//                assertEquals(true, e.getMessage().contains(item));
//            }
//        }
//    }

    private static final String ANY_FOOD = "ANY_FOOD";

    private Fridge fridge;

    @BeforeEach
    void setUp() {
        fridge = new Fridge();
    }

    @Test
    void fridgeContainsOnlyPreviouslyPutFood() {
        fridge.put(ANY_FOOD);
        assertThat(fridge.contains(ANY_FOOD)).isTrue();
    }

    @Test
    void fridgeDoesNotContainNotPreviouslyOutFood() {
        assertThat(fridge.contains(ANY_FOOD)).isFalse();
    }

    @Test
    void takeShouldFailIfThereWasNoSuchFoodInAFridge() {
        assertThatExceptionOfType(NoSuchItemException.class).isThrownBy(() -> fridge.take(ANY_FOOD));
    }

    @Test
    void previouslyPutFoodShouldFailIfTookTwice() {
        fridge.put(ANY_FOOD);
        assertThatNoException().isThrownBy(() -> fridge.take(ANY_FOOD));
        assertThatExceptionOfType(NoSuchItemException.class).isThrownBy(() -> fridge.take(ANY_FOOD));
    }

}