package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;
import org.effectivejava.topics.pojos.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@AutoService(Item.class)
public class PreferAnnotationsToNamingPatters implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_6;
    }

    @Override
    public String getTheme() {
        return "Prefer annotations to naming patterns";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "historically it was common to use Naming patterns to indicate that some program element demand " +
                        "special treatment; for example test methods before Junit5",
                "so, such way has disadvantages: typos, accidentally using",
                "annotations resolve all the problems with Naming pattern; for example Junit5 relies on Annotations",
                "to show this - we will write toy testing framework",
                "firstly - create marker annotation type declaration - Test",
                "Test is annotated with Retention and Target annotations. Such annotations on annotation type " +
                    "declarations are knows as meta-annotations",
                """
                        the comment says: 'use only on parameterless static methods'. To make compiler be able to enforce this -
                        "you should write annotation processor; look at javax.annotation.processing. If you are not doing this -
                        it is your program's runtime problem""",
                "look at SampleTest class as an example - there are 4 methods annotated with Test: one will pass, two" +
                        "will fail, and one is invalid, others should be ignored; see implementation in runExamples()",
                "annotation ExceptionTest is used to mark tests passed if they throw particular exception", // CAN BE AN EXERCISE
                "next, let's add support of multiple exception types tracking",
                "next, improve using Repeatable annotation",
                "so, there is simply no reason to use naming patterns when you can use annotations instead",
                "all programmers should use the predefined annotation types that Java provides, also, consider using " +
                        "annotations provided by ide or static analysis tools",
                ""
        );
    }

    @Override
    public void runExamples() {
        runTests("org.effectivejava.topics.pojos.SampleTest");
        runTests("org.effectivejava.topics.pojos.SampleTest2");
    }

    private void runTests(String className) {
        int tests = 0;
        int passed = 0;
        try {
            Class<?> testClass = Class.forName(className);
            for (Method m : testClass.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Test.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        passed++;
                    } catch (InvocationTargetException wrappedExc) {
                        Throwable exc = wrappedExc.getCause();
                        System.out.println(m + " failed: " + exc);
                    } catch (Exception exc) {
                        System.out.println("Invalid @Test: " + m);
                    }
                }
                if (m.isAnnotationPresent(ExceptionTest.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        System.out.printf("Test %s failed: no exception%n", m);
                    } catch (InvocationTargetException wrappedEx) {
                        Throwable exc = wrappedEx.getCause();
                        Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
                        if (excType.isInstance(exc)) {
                            passed++;
                        } else {
                            System.out.printf("Test %s failed: expected %s, got %s%n", m, excType.getName(), exc);
                        }
                    } catch (Exception exc) {
                        System.out.println("Invalid @ExceptionTest: " + m);
                    }
                }
                if (m.isAnnotationPresent(ExceptionsTest.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        System.out.printf("Test %s failed: no exception%n", m);
                    } catch (InvocationTargetException wrappedEx) {
                        Throwable exc = wrappedEx.getCause();
                        int oldPassed = passed;
                        Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionsTest.class).value();
                        for (Class<? extends Throwable> excType : excTypes) {
                            if (excType.isInstance(exc)) {
                                passed++;
                                break;
                            }
                        }
                        if (passed == oldPassed) {
                            System.out.printf("Test %s failed: %s %n", m, exc);
                        }
                    } catch (Exception exc) {
                        System.out.println("Invalid @ExceptionTest: " + m);
                    }
                }
                // Processing repeatable annotations
                if (m.isAnnotationPresent(RepeatableExceptionTest.class)
                        || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                    tests++;
                    try {
                        m.invoke(null);
                        System.out.printf("Test %s failed: no exception%n", m);
                    } catch (Throwable wrappedExc) {
                        Throwable exc = wrappedExc.getCause();
                        int oldPassed = passed;
                        RepeatableExceptionTest[] excTests =
                                m.getAnnotationsByType(RepeatableExceptionTest.class);
                        for (RepeatableExceptionTest excTest : excTests) {
                            if (excTest.value().isInstance(exc)) {
                                passed++;
                                break;
                            }
                        }
                        if (passed == oldPassed)
                            System.out.printf("Test %s failed: %s %n", m, exc);
                    }
                }
            }
            System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
        } catch (Exception ignored) {}
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
