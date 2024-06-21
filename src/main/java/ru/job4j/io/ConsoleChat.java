package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean answer = true;
        String consoleAnswer;
        String question = "";
        List<String> botAnswers = readPhrases();
        while (!OUT.equals(question)) {
            consoleAnswer = "Введите строку: ";
            System.out.println(consoleAnswer);
            saveLog(List.of(consoleAnswer, System.lineSeparator()));

            Scanner console = new Scanner(System.in);
            question = console.nextLine();
            saveLog(List.of(question, System.lineSeparator()));

            if (STOP.equals(question)) {
                answer = false;
            }

            if (answer && !OUT.equals(question)) {
                int index = (int) (Math.random() * botAnswers.size());
                consoleAnswer = botAnswers.get(index);
                System.out.println(consoleAnswer);
                saveLog(List.of(consoleAnswer, System.lineSeparator()));
            }

            if (CONTINUE.equals(question)) {
                answer = true;
            }
        }

    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            result = reader.lines()
                    .map(string -> string + System.lineSeparator())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(writer::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("C:/projects/job4j_design/data/path.txt",
                "C:/projects/job4j_design/data/botAnswers.txt");
        consoleChat.run();
    }
}