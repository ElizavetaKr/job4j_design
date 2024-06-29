package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        final String delimiter = argsName.get("delimiter");
        File file = new File(argsName.get("path"));
        try (var scanner = new Scanner(file)
                .useDelimiter(",");
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {

            List<String> param = List.of(scanner.nextLine().split(delimiter));
            List<String> filter = List.of(argsName.get("filter").split(","));
            List<Integer> index = new ArrayList<>();

            StringJoiner joiner = new StringJoiner(delimiter);

            for (String str : filter) {
                if (param.contains(str)) {
                    index.add(param.indexOf(str));
                    joiner.add(str);
                }
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(joiner);
            } else {
                output.write((joiner + System.lineSeparator()).getBytes());
            }


            while (scanner.hasNext()) {
                joiner = new StringJoiner(delimiter);
                param = List.of(scanner.nextLine().split(delimiter));

                for (Integer i : index) {
                    joiner.add(param.get(i));
                }
                if ("stdout".equals(argsName.get("out"))) {
                    System.out.println(joiner);
                } else {
                    output.write((joiner + System.lineSeparator()).getBytes());
                }
            }
        }
    }

    private static ArgsName validation(String[] args) {
        ArgsName param = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("The wrong number of parameters is set");
        }
        File file = new File(param.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!param.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("The extension of \"path\" is not set correctly");
        }
        if (!param.get("out").endsWith(".csv") && !param.get("out").equals("stdout")) {
            throw new IllegalArgumentException("The extension of \"out\" is not set correctly");
        }
        return param;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = validation(args);
        handle(argsName);
    }
}