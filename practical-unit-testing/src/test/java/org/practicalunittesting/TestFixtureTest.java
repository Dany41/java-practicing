package org.practicalunittesting;

import org.junit.jupiter.api.*;

public class TestFixtureTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach()
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    void testMethodA() {
        System.out.println("method A");
    }
    @Test
    void testMethodB() {
        System.out.println("method B");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }
}
