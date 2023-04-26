package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import lombok.SneakyThrows;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AutoService(Item.class)
public class OverrideHashCode implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_3;
    }

    @Override
    public String getTheme() {
        return "Always override hashCode when you override equals";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                """
                    HashCode's contract:
                    \ta) it must consistently return the same value during the application life, provided that no information
                    \t\tin equals comparison is modified. It can differ from one execution of application to another
                    \tb) if two objects are equal according to equal method, then they must have the same hashCode int value
                    \tc) if two objects are unequal according to equals method, it is not required for objects to have the same hashCode int value
                    """,
                "if the process to calculate the hashCode is long, make it lazily evaluated (be careful with thread-safety)",
                "Integer and String failed to provide the good hashCode functions"
        );
    }

    @Override
    public void runExamples() {

        Map<BadPojoWithTwoFields, String> badPojoMap = new HashMap<>();
        BadPojoWithTwoFields badPojo = new BadPojoWithTwoFields(1, "the first");
        badPojoMap.put(badPojo, "bad pojo is dead");

        Map<GoodPojoWithTwoFields, String> goodPojoMap = new HashMap<>();
        GoodPojoWithTwoFields goodPojo = new GoodPojoWithTwoFields(1, "pojjjjoooo");
        goodPojoMap.put(goodPojo, "good pojo is here");

        System.out.println();
        System.out.println("Trying to use BadPojoWithTwoFields in hashMap, does it contain the bad pojo?");
        System.out.println(badPojoMap.containsKey(badPojo));
        System.out.println(badPojoMap.containsKey(new BadPojoWithTwoFields(1, "the first")));

        System.out.println();
        System.out.println("Trying to use GoodPojoWithTwoFields in hashMap, does it contain the good pojo?");
        System.out.println(goodPojoMap.containsKey(goodPojo));
        System.out.println(goodPojoMap.containsKey(new GoodPojoWithTwoFields(1, "pojjjjoooo")));
    }

    private static class BadPojoWithTwoFields {
        private final int number;
        private final String name;

        public BadPojoWithTwoFields(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof GoodPojoWithTwoFields gp)) return false;
            return this.number == gp.number && this.name.equals(gp.name);
        }
    }

    private static class GoodPojoWithTwoFields {
        private final int number;
        private final String name;

        public GoodPojoWithTwoFields(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof GoodPojoWithTwoFields gp)) return false;
            return this.number == gp.number && this.name.equals(gp.name);
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + Integer.hashCode(number); // optimized to result << 31
            result = 31 * result + (name == null ? 0 : name.hashCode()); // optimized to result << 31
            return result;
//            return Objects.hash(number, name);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        URL google = new URL("https://google.com");
        Thread.sleep(300_000);
        URL huta = new URL("https://google.com");
        System.out.println(google.equals(huta));
    }

    private static class LazyPojoWithTwoFields {
        private final int number;
        private final String name;

        public LazyPojoWithTwoFields(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof GoodPojoWithTwoFields gp)) return false;
            return this.number == gp.number && this.name.equals(gp.name);
        }

        private int hashCode; // default value for int primitive is 0

        @Override
        public int hashCode() {
            int result = hashCode;
            if (result == 0) {
                result = 1;
                result = 31 * result + Integer.hashCode(number); // optimized to result << 31
                result = 31 * result + (name == null ? 0 : name.hashCode()); // optimized to result << 31
            }
            return result;
        }
    }
}
