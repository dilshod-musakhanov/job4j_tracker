package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] l =  o1.split("/");
        String[] r =  o2.split("/");
        int res = r[0].compareTo(l[0]);
        int size = Math.min(l.length, r.length);
        if (res == 0) {
            for (int i = 0; i < size; i++) {
                res = l[i].compareTo(r[i]);
                if (res != 0) {
                    break;
                }
            }
        }
        if (res == 0) {
            res = Integer.compare(l.length, r.length);
        }
         return res;
    }
}