package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        final Calendar birthday = Calendar.getInstance();
        User o1 = new User("Ivan", 2, birthday);
        int hC1 = o1.hashCode();
        int hash1 = hC1 ^ (hC1 >>> 16);
        int bucket1 = hash1 & 15;
        User o2 = new User("Ivan", 2, birthday);
        int hC2 = o2.hashCode();
        int hash2 = hC2 ^ (hC2 >>> 16);
        int bucket2 = hash2 & 15;
        Map<User, Object> map = new HashMap<>();
        map.put(o1, new Object());
        map.put(o2, new Object());
        System.out.println(map);
        System.out.println(bucket1 + " " + bucket2);
        System.out.println(hC1 + " " + hC2);
    }
}

