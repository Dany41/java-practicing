package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseEnumSetInsteadOfBitFields implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Use EnumSet instead of bit fields";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "if the elements of an enumerated type are used primarily insets, it is traditional to use the\n" +
                        "int enum pattern, assigning a different power of 2 to each constant (see Text enum below)",
                "such approach lets you use the bitwise OR operation to combine several constants into a set, known as\n" +
                        "a bit field ('|', '&'). This approach is more effective, for example, to be used in sets",
                "but there is EnumSet class created for this purpose, it implements Set interface, but internally it is\n" +
                        "a bit vector",
                "just because an enumerated type will be used in sets, there is no reason to represent it with bit fields"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return Item.super.examplesInCode();
    }

    // Bit field enumeration constants - OBSOLETE!
    public static class Text {
        public static final int STYLE_BOLD = 1 << 0; // 1
        public static final int STYLE_ITALIC = 1 << 1; // 2
        public static final int STYLE_UNDERLINE = 1 << 2; // 4
        public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8
        // Parameter is bitwise OR of zero or more STYLE_ constants
//        public void applyStyles(int styles) { ... }
//        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }

    // EnumSet - a modern replacement for bit fields
    public static class TextRefactored {
        public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }
        // Any Set could be passed in, but EnumSet is clearly best
//        public void applyStyles(Set<Style> styles) { ... }
//        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
