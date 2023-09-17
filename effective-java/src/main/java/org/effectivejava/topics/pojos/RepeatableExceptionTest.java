package org.effectivejava.topics.pojos;

import java.lang.annotation.*;

// Repeatable annotation type
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface RepeatableExceptionTest {
    Class<? extends Exception> value();
}
