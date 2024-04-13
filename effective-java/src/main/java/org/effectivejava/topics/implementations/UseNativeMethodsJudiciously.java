package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;

@AutoService(Item.class)
public class UseNativeMethodsJudiciously implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Use native methods judiciously";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Java Native Interface (JNI) allows Java programs to call native methods, which are written in native " +
                        "programming languages such as C or C++",
                """
                        there are three main reasons for using native methods:
                            - they provide access to platform-specific facilities such as registries
                            - they provide access to existing libraries of native code, including legacy libraries that
                                provide access to legacy data
                            - they are used to write performance-critical parts of applications in native languages for
                                improved performance""",
                "it is rarely advisable to use native methods for improved performance"
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
