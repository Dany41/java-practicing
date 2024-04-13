package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.Date;
import java.util.List;

@AutoService(Item.class)
public class MakeDefensiveCopiesWhenNeeded implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_8;
    }

    @Override
    public String getTheme() {
        return "Make defensive copies when needed";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "java is safe :) if not using native methods it is immune to buffer overruns, array overruns, " +
                        "wild pointers, and other memory corruption errors",
                "you must program defensively, with the assumption that clients of your class will do their best to " +
                        "destroy its invariants",
                "look at, at first glance, safe class Period below",
                "Date is obsolete and should no longer be used in new code",
                "to protect the internals of a Period instance from this sort of attack, it is essential to make a " +
                        "defensive copy of each mutable parameter to the constructor",
                """
                        defensive copies are made before checking the validity of the parameters, and the validity check
                        is performed on the copies rather than on the originals. It protects the class against changes
                        to the parameters from another thread during the window of vulnerability between the time the
                        parameters are checked and time they are copied. In the computer security community, this is
                        knows as a time-of-check/time-of-use or TOCTOU attack""",
                "clone() is restricted to use, because it can return the subclass of Date which is untrusted",
                "to prevent this sort of attack, do not use the clone method to make a defensive copy of a parameter " +
                        "whose type is subclassable by untrusted parties",
                "but it is still can be attacked, see the second attack below",
                "to prevent this 0 modify the accessors to return defensive copies of mutable internal fields"
        );
    }

    @Override
    public void runExamples() {
        // Attack the internals of a Period instance
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78); // Modifies internals of p!

        // Second attack on the internals of a Period instance
        Date start2 = new Date();
        Date end2 = new Date();
        Period p2 = new Period(start2, end2);
        p2.end().setYear(78); // Modifies internals of p!
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Broken "immutable" time period class
    private static final class Period {
        private final Date start;
        private final Date end;
        /**
         * @param start the beginning of the period
         * @param end the end of the period; must not precede start
         * @throws IllegalArgumentException if start is after end
         * @throws NullPointerException if start or end is null
         */
        public Period(Date start, Date end) {
            if (start.compareTo(end) > 0)
                throw new IllegalArgumentException(
                        start + " after " + end);
            this.start = start;
            this.end = end;
        }
        public Date start() {
            return start;
        }

        public Date end() {
            return end;
        }



        // Repaired constructor - makes defensive copies of parameters
//        public Period(Date start, Date end) {
//            this.start = new Date(start.getTime());
//            this.end = new Date(end.getTime());
//            if (this.start.compareTo(this.end) > 0)
//                throw new IllegalArgumentException(
//                        this.start + " after " + this.end);
//        }

        // Repaired accessors - make defensive copies of internal fields
//        public Date start() {
//            return new Date(start.getTime());
//        }
//        public Date end() {
//            return new Date(end.getTime());
//        }
    }
}
