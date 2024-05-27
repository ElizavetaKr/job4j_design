package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        Map<Integer, String> map = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            String userCurrent = map.get(user.getId());
            if (!user.getName().equals(userCurrent)) {
                if (userCurrent != null) {
                    result.setChanged(result.getChanged() + 1);
                } else {
                    result.setDeleted(result.getDeleted() + 1);
                }
            }
        }
        result.setAdded(Math.abs(previous.size() - current.size() - result.getDeleted()));

        return result;
    }

}