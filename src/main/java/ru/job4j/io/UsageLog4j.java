package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        boolean b = false;
        byte by = 1;
        short s = 3;
        char c = 'a';
        int i = 56;
        long l = 1000L;
        float f = 1.2f;
        double d = 2.5;

        LOG.debug("Boolean: {}, byte: {}, short: {}, char: {}, int: {}, long: {}, float: {}, double: {}",
                b, by, s, c, i, l, f, d);
    }
}