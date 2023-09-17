package org.effectivejava.topics.pojos;


public class CustomAnnotationProcessorCheck {
    @org.testframework.annotation.Test
    public static void method() {
        // should generate in target class with name CustomAnnotationProcessorCheck_CustomGenerated
    }

    public static void main(String[] args) {
        System.out.println(new CustomAnnotationProcessorCheck_CustomGenerated().generatedMethod());
    }
}
