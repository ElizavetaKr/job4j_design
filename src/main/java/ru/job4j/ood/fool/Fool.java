package ru.job4j.ood.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(compare(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!compare(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    static String compare(int startAt) {
        String result = "";
        if (startAt % 3 == 0) {
            result += "Fizz";
        }
        if (startAt % 5 == 0) {
            result += "Buzz";
        }
        if (startAt % 3 != 0 && startAt % 5 != 0) {
            result += startAt;
        }
        return result;
    }
}