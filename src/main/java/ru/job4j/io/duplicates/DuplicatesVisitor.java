package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<String>> map = new HashMap<>();

    @Override

    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {

        Path path = file.toAbsolutePath();
        FileProperty newFile = new FileProperty(path.toFile().length(), path.getFileName().toString());
        if (map.containsKey(newFile) && map.get(newFile) != null) {
            map.get(newFile).add(path.toString());
        } else {
            map.computeIfAbsent(newFile, key -> {
                List<String> list = new ArrayList<>();
                list.add(path.toString());
                return list;
            });
        }


        map.forEach((k, v) -> {
            if (v.size() > 1) {
                System.out.println(k.getName() + " - " + k.getSize());
                v.forEach(System.out::println);
            }
        });

        return super.visitFile(file, attributes);
    }
}