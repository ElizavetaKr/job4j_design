package ru.job4j.gc.cache;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        validateDir(cachingDir);
        this.cachingDir = cachingDir;
    }

    private void validateDir(String dirName) {
        File file = new File(dirName);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Файл %s не существует.", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - не директория", file.getAbsoluteFile()));
        }
    }

    private void validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Файл %s не существует.", file.getAbsoluteFile()));
        }
        if (!file.canRead()) {
            throw new IllegalArgumentException("Доступ к чтению ограничен.");
        }
        if (!file.canWrite()) {
            throw new IllegalArgumentException("Доступ к записи ограничен.");
        }
    }

    @Override
    protected String load(String key) {
       String result = "";
       validateFile(key);
        try (FileInputStream input = new FileInputStream(key)) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            result = text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}