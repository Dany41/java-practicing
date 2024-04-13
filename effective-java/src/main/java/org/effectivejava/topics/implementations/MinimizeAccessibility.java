package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Arrays;
import java.util.List;

@AutoService({Item.class})
public class MinimizeAccessibility implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_4;
    }

    @Override
    public String getTheme() {
        return "Minimize the accessibility of classes and members";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "Well-designed component hides all its implementation details from other components, cleanly\n" +
                        "separating its API from its implementation. This concept is known as Information Hiding or Encapsulation (Parnas72)",
                "The main benefit of such approach is decoupling between components, which allowing them to be:\n" +
                        "developed, tested, optimized, used, understood, modified in isolation",
                "The rule of using the accessibility: make each class or member as inaccessible as possible",
                "The top level class can be package-private or public",
                "If you make it public you are obligated to support it forever to maintain compatibility",
                "If a package-private class is used only by one class: think to make it inner static class of that class",
                """
                        Possible access levels for members:
                            1) private - accessible only from the class where it is declared
                            2) package-private - accessible from any class within its package
                            3) protected - accessible from any class within its package + from subclasses
                            4) public - accessible from anywhere
                        """,
                "Instance fields of public classes should rarely be public, because you cannot control its possible modifying\n" +
                        "With getter you can have at least some control. So, classes with public mutable fields are not thread safe",
                "Nonzero-length array is always mutable",
                "It is wrong for a class to have a public static final array field, or an accessor that returns such a field",
                "From Java 9 modules were added. Module is a grouping of packages. It can explicitly export the package",
                "Summary: reduce accessibility of program elements as much as possible (within reason)"
        );
    }

    @Override
    public void runExamples() {
        System.out.println(Arrays.toString(PoorSecretsHolder.VALUES));
        PoorSecretsHolder.VALUES[0] = new PoorSecretsHolder.Password("haha new password, just broke your security design");
        System.out.println();
        System.out.println(Arrays.toString(PoorSecretsHolder.VALUES));
    }

    private static class PoorSecretsHolder {

        public static final Password[] VALUES = {
                new Password("secret1"),
                new Password("secret2"),
                new Password("secret3")
        };

        private static class Password {
            private char[] chars;

            public Password(String secret) {
                this.chars = secret.toCharArray();
            }

            @Override
            public String toString() {
                return "Password(" + new String(chars) + ')';
            }
        }

    }
}
