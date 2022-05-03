package ru.job4j.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = (i, s) -> map.put(i, s);
        BiPredicate<Integer, String> biPred = (i, s) -> i % 2 == 0 || s.length() == 4;
        Supplier<List<String>> sup = () -> new ArrayList<>(map.values());
        Consumer<String> con = s -> System.out.println(s);
        Function<String, String> func = s -> s.toUpperCase();

        biCon.accept(1, "one");
        biCon.accept(2, "two");
        biCon.accept(3, "three");
        biCon.accept(4, "four");
        biCon.accept(5, "five");
        System.out.println("Elements in map: " + map);
        for (Integer i : map.keySet()) {
            if (biPred.test(i, map.get(i))) {
                System.out.println("key: " + i + " value: " + map.get(i));
            }
        }
        List<String> strings = sup.get();
        for (String s : strings) {
            con.accept(func.apply(s));
        }
    }
}
