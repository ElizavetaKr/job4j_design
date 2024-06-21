package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        for (Path path : sources) {
            packSingleFile(path.toFile(), target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validation(String[] args) {
        ArgsName param = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("The wrong number of parameters is set");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The extension is not set correctly");
        }
        if (!args[2].endsWith(".zip")) {
            throw new IllegalArgumentException("The archive name is not set correctly");
        }
        return param;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName param = validation(args);
        List<Path> sources = Search.search(Paths.get(param.get("d")), path -> path.toFile().getName().endsWith(param.get("e")));
        zip.packFiles(sources, new File(param.get("o")));
    }
}