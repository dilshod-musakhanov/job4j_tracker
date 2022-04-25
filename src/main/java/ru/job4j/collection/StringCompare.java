package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        char[] l = left.toCharArray();
        char[] r = right.toCharArray();
        int result = 0;
        if (l.length != r.length) {
            for (int i = 0; i < l.length; i++) {
                int rsl = Character.compare(l[i], r[i]);
                if (rsl != 0) {
                    result = rsl;
                    break;
                }
                result = Integer.compare(l.length, r.length);
            }
        } else  {
            for (int i = 0; i < l.length; i++) {
                int rsl = Character.compare(l[i], r[i]);
                if (rsl != 0) {
                    result = rsl;
                    break;
                }
            }
        }
        return result;
    }
}
