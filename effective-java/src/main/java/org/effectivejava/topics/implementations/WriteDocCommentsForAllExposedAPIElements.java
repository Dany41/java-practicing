package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class WriteDocCommentsForAllExposedAPIElements implements Item {

    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Write doc comments for all exposed API elements";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "conventions how to write a doc can be found in How to Write Doc Comments web page",
                """
                    that page wasn't updated since Java 4, so there are missing the next doc tags: {@index} added in
                    Java 9, {@implSpec} added in Java 8, {@literal} and {@code} added in Java 5. But the doc is still
                    valuable, missing tags described here""",
                "to document your API properly, you must precede every exported class, interface, constructor, method, " +
                        "and field declaration with a doc comment",
                "the doc comment for a method should describe succinctly the contract between the method and its client",
                "it should: describe what, not how, enumerate preconditions, postconditions, side effects, has @param" +
                        " tag for every param, @return, @throws for every (all types of exceptions)",
                "text after tags @param, @return, and @throws are not terminated by a dot by convention",
                "you can use html tags in java docs, because every java doc firstly translated to html, and then this " +
                        "html is processed",
                "@code is used to write code fragments in javadoc, you can use in it also symbols '<' and '>' and it " +
                        "will not affect html",
                "to write multiline code use tag @code wrapped in html tag <pre> - <pre>{@code code}</pre>",
                "@implSpec describe the contract between a method and its subclass, allowing subclasses to rely on " +
                        "implementation behavior if they inherit the method or call it via super",
                "the best way to get special characters ('<', '>', '&') into documentation is to surround them with the " +
                        "{@literal} tag, which suppress processing of HTML markup and nested Javadoc tags",
                "doc comments should be reachable both in the source code and in the generated documentation",
                "no two members or constructors in a class or interface should have the same summary description, " +
                        "except overloading which have the same first sentence, of course",
                "the first sentence ends with '. ' (dot and space symbols), be careful - Javadoc will end it on its own",
                "using @index tag you can index the words, and then it will appear in drop-down menu of matching pages",
                "classes, methods, and fields are indexed automatically",
                "when documenting a generic type or method, be sure to document all type parameters",
                "when documenting an enum type, be sure to document the constants",
                "when documenting an annotation type, be sure to document any members",
                "whether or not a class or static method is thread-safe, you should document its thread-safety",
                "Javadoc can be inherited if the doc is not provided, algorithm of searching is described in " +
                        "The Javadoc Reference Guide"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
