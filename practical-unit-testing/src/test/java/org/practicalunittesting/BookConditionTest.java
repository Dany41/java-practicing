package org.practicalunittesting;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookConditionTest {

    private static final String ENGLISH = "English";

    private Book book;

    Condition<Book> writtenInEnglish = new Condition<>(
            book -> book.getLanguage().equals(ENGLISH),
            "book in English"
    );

    @Test
    void languageCheck() {
        Book book = mock(Book.class);
        when(book.getLanguage()).thenReturn(ENGLISH);
        assertThat(book).is(writtenInEnglish);
    }

}
