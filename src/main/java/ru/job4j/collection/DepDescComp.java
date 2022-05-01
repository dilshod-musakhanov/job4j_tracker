package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] l =  o1.split("/");
        String[] r =  o2.split("/");
        int res = r[0].compareTo(l[0]);
        return res != 0 ? res : o1.compareTo(o2);
    }
}