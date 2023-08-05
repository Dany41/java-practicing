package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StackTest {

    Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void createdStackShouldBeEmpty() {
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void addingAnElementShouldMakeStackNonEmpty() {
        stack.add(1);

        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    void stackShouldContainAddedElements() {
        stack.add(1);

        assertThat(stack.contains(1)).isTrue();
    }

    @Test
    void stackShouldNotContainOtherElementsExceptAdded() {
        assertThat(stack.contains(2)).isFalse();
    }

    @Test
    void stackShouldContainAddedElementsWhenThereAreLotsOfThem() {
        IntStream.range(1, 100).forEach(stack::add);

        assertThat(stack.contains(1)).isTrue();
        assertThat(stack.contains(5)).isTrue();
        assertThat(stack.contains(19)).isTrue();
        assertThat(stack.contains(99)).isTrue();

        assertThat(stack.contains(-1)).isFalse();
        assertThat(stack.contains(-5)).isFalse();
        assertThat(stack.contains(100)).isFalse();
        assertThat(stack.contains(190)).isFalse();
    }

    @Test
    void sizeShouldIncreaseByOneWhenAddElementToTheStack() {
        assertThat(stack.size()).isEqualTo(0);
        stack.add(1);
        assertThat(stack.size()).isEqualTo(1);
        stack.add(2);
        assertThat(stack.size()).isEqualTo(2);
    }

    @Test
    void popShouldReturnLastInElement() {
        stack.add(1);
        stack.add(2);
        stack.add(3);

        Integer lastInElement = stack.pop();

        assertThat(lastInElement).isEqualTo(3);
    }

    @Test
    void popShouldDecreaseSize() {
        stack.add(1);
        stack.add(2);
        stack.add(3);

        assertThat(stack.size()).isEqualTo(3);

        stack.pop();

        assertThat(stack.size()).isEqualTo(2);
    }

    @Test
    void popShouldDeleteElementFromStack() {
        stack.add(1);
        stack.add(2);
        stack.add(3);

        assertThat(stack.pop()).isEqualTo(3);
        assertThat(stack.pop()).isEqualTo(2);
    }

    @Test
    void popOnEmptyStackShouldThrowException() {
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::pop);
    }

}