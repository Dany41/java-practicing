package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class MinimizeTheScopeOfLocalVariables implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_9;
    }

    @Override
    public String getTheme() {
        return "Minimize the scope of local variables";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "the most powerful technique for minimizing the scope of a local variable is to declare it where it is " +
                        "first used",
                "nearly every local variable declaration should contain an initializer",
                "prefer for loops to while loops, assuming the contents of the loop variable aren't needed after the " +
                        "loop terminates, because for loop minimizes the scope of the variables used"
        );
    }

    @Override
    public void runExamples() {
        // no compile-time error
//        Iterator<Element> i = c.iterator();
//        while (i.hasNext()) {
//            doSomething(i.next());
//        }
////        ...
//        Iterator<Element> i2 = c2.iterator();
//        while (i.hasNext()) { // BUG!
//            doSomethingElse(i2.next());
//        }

        // compile-time error
//        for (Iterator<Element> i = c.iterator(); i.hasNext(); ) {
//            Element e = i.next();
//... // Do something with e and i
//        }
//...
//// Compile-time error - cannot find symbol i
//        for (Iterator<Element> i2 = c2.iterator(); i.hasNext(); ) {
//            Element e2 = i2.next();
//... // Do something with e2 and i2
//        }
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
