package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class UseStreamsJudiciously implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Use streams judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Stream API provides two key abstractions: stream, and stream pipeline",
                "stream pipeline followed by zero or more intermediate operations and one terminal operation",
                "stream pipelines are evaluated lazily, evaluation doesn't start until the terminal operation is invoked",
                "by default, stream pipelines run sequentially",
                "overusing streams makes programs hard to read and maintain",
                "in the absence of explicit types, careful naming of lambda parameters is essential to the readability " +
                        "of stream pipelines",
                "using helper method is even more important for readability in stream pipelines than in iterative code",
                "refrain from using streams to process char values, because chars() method from String returns " +
                        "actually int, and it confuses",
                "refactor existing code to use streams and use them in new code only where it makes sense to do so",
                """
                        there are several things you can do from a code block, but not in streams:
                            read or modify any local variable in scope; from lambda you can read any final or effectively
                                final variables, but not modify them (JLS 4.12.4)
                            return from the enclosing method, break or continue an enclosing loop, or throw any checked
                                exception; from lambda you can do none of these things
                        """,
                """
                        streams make it easy to do the following things:
                            uniformly transform sequences of elements
                            filter sequences of elements
                            combine sequences of elements using a single operation (for example to add them, concatenate
                                them, or compute their minimum)
                            accumulate sequences of elements into a collection, perhaps grouping them by so common attribute
                            search a sequence of elements for an element satisfying some criterion""",
                "if you are not sure whether a task is better served by streams or iteration, try both and see which " +
                        "works better"
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
