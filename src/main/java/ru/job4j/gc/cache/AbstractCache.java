package ru.job4j.gc.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        File file = new File(String.valueOf(key));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Файл %s не существует.", file.getAbsoluteFile()));
        }
        if (!file.canRead()) {
            throw new IllegalArgumentException("Доступ к чтению ограничен.");
        }
        if (!file.canWrite()) {
            throw new IllegalArgumentException("Доступ к записи ограничен.");
        }
        SoftReference<V> ref = cache.getOrDefault(key, new SoftReference<>(load(key)));
        cache.put(key, ref);
        return ref.get();
    }

    protected abstract V load(K key);

}
