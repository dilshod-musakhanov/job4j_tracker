package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> data = new HashMap<>();
        data.put("alibaba@alibaba.com", "Ali Baba");
        data.put("chaplin@chaplin.com", "Charlie Chaplin");
        for (String key : data.keySet()) {
            String value = data.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
