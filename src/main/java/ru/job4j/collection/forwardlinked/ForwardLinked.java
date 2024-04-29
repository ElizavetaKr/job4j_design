package ru.job4j.collection.forwardlinked;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        modCount++;
        final Node<T> newNode = new Node<>(value, null);
        final Node<T> first = head;
        if (first == null) {
            head = newNode;
        } else {
            while (head.next != null) {
                head = head.next;
            }
            head.next = newNode;
            head = first;
        }
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }

        return result.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        modCount--;
        size--;
        final T result = head.item;
        head = head.next;

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int count = modCount;
            private int point;

            Node<T> element = head;

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = element.item;
                element = element.next;
                point++;
                return result;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}