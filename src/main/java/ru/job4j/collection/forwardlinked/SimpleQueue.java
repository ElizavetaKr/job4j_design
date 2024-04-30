package ru.job4j.collection.forwardlinked;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    int size;
    int out = 0;
    int in = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (in > 0) {
            for (int i = size; i > 0; i--) {
                output.push(input.pop());
            }
            in = 0;
        }
        size--;
        out++;
        return output.pop();
    }

    public void push(T value) {
        if (out > 0) {
            for (int i = size; i > 0; i--) {
                input.push(output.pop());
            }
            out = 0;
        }
        input.push(value);
        size++;
        in++;
    }
}