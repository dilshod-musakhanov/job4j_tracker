package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int size = Math.min(left.length(), right.length());
        int result = 0;
        for (int i = 0; i < size; i++) {
            int rsl = Character.compare(left.charAt(i), right.charAt(i));
            if (rsl != 0) {
                result = rsl;
                break;
            } else {
                result = Integer.compare(left.length(), right.length());
            }
        }
        return result;
    }
}
