package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.practicalunittesting.MyProjectAssertions.assertThat;

public class CustomMatchers {

    public static final String ANY_TITLE = "title";

    @Test
    void customAssertionChecking() {
        Book book = mock(Book.class);
        when(book.getTitle()).thenReturn(ANY_TITLE);
        assertThat(book).hasTitle(ANY_TITLE);
        assertThat(true).isTrue();
    }
}
