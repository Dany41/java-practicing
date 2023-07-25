package org.practicalunittesting;

public interface Book {
    String getTitle();

    default String getLanguage() {
        return "English";
    }
}
