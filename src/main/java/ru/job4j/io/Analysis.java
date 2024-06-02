package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter output = new PrintWriter(new FileOutputStream(target));
             BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            boolean work = true;
            while ((line = reader.readLine()) != null) {
                if (work) {
                    if (line.contains("400") || line.contains("500")) {
                        output.write(line.substring(4) + ";");
                        work = false;
                    }
                } else if (!line.contains("400") && !line.contains("500")) {
                    output.write(line.substring(4) + System.lineSeparator());
                    work = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}