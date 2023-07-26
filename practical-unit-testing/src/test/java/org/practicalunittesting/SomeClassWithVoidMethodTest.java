package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class SomeClassWithVoidMethodTest {

    SomeClassWithVoidMethod someObject = mock(SomeClassWithVoidMethod.class);

    @Test
    void mockVoidMethod() {
//        doNothing()
//        doAnswer()
        doThrow(RuntimeException.class)
                .when(someObject).someMethod();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(someObject::someMethod);
    }


}