package ru.job4j.collection.forwardlinked;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int size;
    private int out = 0;
    private int in = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (out == 0 && in > 0) {
            for (int i = size; i > 0; i--) {
                output.push(input.pop());
            }
            out = in;
        }
        out--;
        size--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        size++;
        in++;
    }
}