package org.effectivejava.topics.helpers;

public enum Chapter {
    CHAPTER_2("Creating and Destroying Objects"),
    CHAPTER_3(""),
    CHAPTER_4(""),
    CHAPTER_5(""),
    CHAPTER_6(""),
    CHAPTER_7(""),
    CHAPTER_8(""),
    CHAPTER_9(""),
    CHAPTER_10(""),
    CHAPTER_11(""),
    CHAPTER_12("");

    private String fullName;

    Chapter(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }
}
