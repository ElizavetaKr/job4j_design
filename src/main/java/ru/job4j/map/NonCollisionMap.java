package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count * 1.0 / capacity >= LOAD_FACTOR) {
            expand();
        }
        int position = index(key);
        MapEntry<K, V> map = new MapEntry<>(key, value);
        if (table[position] == null) {
            table[position] = map;
            count++;
            modCount++;
        }
        return table[position] == map;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                int position = index(element.key);
                newTable[position] = element;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int position = index(key);
        return keyEquals(key)
                ? table[position].value : null;
    }

    @Override
    public boolean remove(K key) {
        int position = index(key);
        boolean result = keyEquals(key);
        if (result) {
            table[position] = null;
            count--;
            modCount++;
        }
        return result;
    }

    private boolean keyEquals(K key) {
        int position = index(key);
        return table[position] != null
                && Objects.hashCode(key) == Objects.hashCode(table[position].key)
                && Objects.equals(key, table[position].key);
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<>() {

            final int countIterator = modCount;
            private int point;

            @Override
            public boolean hasNext() {
                if (countIterator != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };

    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}