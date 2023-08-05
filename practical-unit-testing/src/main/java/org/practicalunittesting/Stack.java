package org.practicalunittesting;


import java.util.EmptyStackException;

public class Stack<T> {

    private int size;
    Node<T> head;

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T i) {
        head = new Node<>(i, head);
        size++;
    }

    public boolean contains(T element) {
        Node<T> node = head;
        while (node != null) {
            if (node.element.equals(element)) return true;
            node = node.next;
        }
        return false;
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        this.size--;
        T deletedElement = head.element;
        head = head.next;
        return deletedElement;
    }

    public int size() {
        return this.size;
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
