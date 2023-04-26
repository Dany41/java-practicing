package org.effectivejava.serviceloader;

@Chosen
public class Implementation2 implements ServiceInterface {
    @Override
    public void test() {
        System.out.println();
        System.out.println("Implementation 2");
    }
}
