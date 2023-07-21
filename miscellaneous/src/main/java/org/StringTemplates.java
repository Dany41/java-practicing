//package org;
//
//import static java.lang.StringTemplate.STR;
//
///**
// * Reference https://openjdk.org/jeps/430
// */
//public class StringTemplates {
//    public static void main(String[] args) {
//        // motivation
//
//        // String concatenation with the + operator produces hard-to-read code
//        int x = 10;
//        int y = 20;
//        String s = x + " plus " + y + " equals " + (x + y);
//
//        // StringBuilder is verbose
//        String s2 = new StringBuilder()
//                .append(x)
//                .append(" plus ")
//                .append(y)
//                .append(" equals ")
//                .append(x + y)
//                .toString();
//
//
//        // String::format and String::formatted separate the format string from the parameters,
//        // inviting arity and type mismatches
//        String s3 = String.format("%2$d plus %1$d equals %3$d", x, y, x + y);
//        String t = "%2$d plus %1$d equals %3$d".formatted(x, y, x + y);
//
//        // examples in other languages
////    C#             $"{x} plus {y} equals {x + y}"
////    Visual Basic   $"{x} plus {y} equals {x + y}"
////    Python         f"{x} plus {y} equals {x + y}"
////    Scala          s"$x plus $y equals ${x + y}"
////    Groovy         "$x plus $y equals ${x + y}"
////    Kotlin         "$x plus $y equals ${x + y}"
////    JavaScript     `${x} plus ${y} equals ${x + y}`
////    Ruby           "#{x} plus #{y} equals #{x + y}"
////    Swift          "\(x) plus \(y) equals \(x + y)"
//
//        // ST template processor
//        // Embedded expressions can be strings
//        String firstName = "Bill";
//        String lastName = "Duck";
//        String fullName = STR."\{firstName} \{lastName}";
////| "Bill Duck"
//        String sortName = STR."\{lastName}, \{firstName}";
////| "Duck, Bill"
//        System.out.println(sortName);
//
//        // Embedded expressions can perform arithmetic
//        String s5 = STR."\{x} + \{y} = \{x + y}";
////| "10 + 20 = 30"
//
//        // Embedded expressions can invoke methods and access fields
//        String s6 = STR."You have a \{getOfferType()} waiting for you!";
////| "You have a gift waiting for you!"
//        String t2 = STR."Access at \{req.date} \{req.time} from \{req.ipAddress}";
////| "Access at 2022-03-25 15:34 from 8.8.8.8"
//    }
//}
