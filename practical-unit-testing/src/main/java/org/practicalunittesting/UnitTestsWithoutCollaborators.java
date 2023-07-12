package org.practicalunittesting;

import java.util.ArrayList;
import java.util.List;

public class UnitTestsWithoutCollaborators {

    // todo: summarize into one interface

    // todo: to discuss
    // todo: project structure / name convention
    // todo: default plugin for mvn test - https://maven.apache.org/surefire/maven-surefire-plugin/
    // todo: surefire github repository - https://github.com/apache/maven-surefire
    // todo: mvn site default / with surefire-report
    // todo: surefire report first page to check - https://maven.apache.org/surefire/maven-surefire-report-plugin/usage.html
    // todo: check the exercises



    public static String reverse(String s) {
        List<String> tempArray = new ArrayList<String>(s.length());
        for (int i = 0; i < s.length(); i++) {
            tempArray.add(s.substring(i, i+1));
        }
        StringBuilder reversedString = new StringBuilder(s.length());
        for (int i = tempArray.size() -1; i >= 0; i--) {
            reversedString.append(tempArray.get(i));
        }
        return reversedString.toString();
    }


}
