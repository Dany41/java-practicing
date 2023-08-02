package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomTestInstancePreConstructCallback.class)
public class PreConstructCallbackTest {

    @Test
    void testPrintSomething() {
        System.out.println("In test method");
    }
    @Test
    void testAnotherPrintSomething() {
        System.out.println("In another test method");
    }
}
