package com.cat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String age;
    private String street;

    public User() {
    }

    public static void main(String[] args) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("age", "22");
        map.put("street", "25");
        map.put("house", "234");
        map.put("city", "Kiev");
        fillUser(map);
    }

    public static void fillUser(Map<String, String> map) throws IllegalAccessException {
        User user = new User();
        Field[] fields = user.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String string = fields[i].toString();
            String[] strings = string.split("\\.");
            if (map.containsKey(strings[strings.length - 1])) {
                fields[i].set(user, map.get(strings[strings.length - 1]));
            }
        }
        System.out.println(user);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
