package ru.job4j.collection.forwardlinked;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
linked.addFirst(value);
    }
}
