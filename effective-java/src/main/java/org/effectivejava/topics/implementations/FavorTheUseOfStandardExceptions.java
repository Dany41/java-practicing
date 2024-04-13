package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class FavorTheUseOfStandardExceptions implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_10;
    }

    @Override
    public String getTheme() {
        return "Favor the use of standard exceptions";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "do not reuse Exception, RuntimeException, Throwable, or Error directly",
                """
                    here is the list of most commonly reused exceptions:
                        IllegalArgumentException -> non-null parameter value is inappropriate
                        IllegalStateException -> object state is inappropriate for method invocation
                        NullPointerException -> parameter value is null where prohibited
                        IndexOutOfBoundException -> index parameter value is out of range
                        ConcurrentModificationException -> concurrent modification of an object has been detected where
                                                            it is prohibited
                        UnsupportedOperationException -> object does no support method""",
                "throw IllegalStateException if no argument values would have worked, otherwise throw " +
                        "IllegalArgumentException"
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
