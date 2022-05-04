package ru.job4j.lambda;

import java.util.function.Supplier;

public class ScopeInside {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int n : number) {
            int t = total;
            total = add(
                    () -> t + n
            );
        }
        System.out.println(total);
    }

    public static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }
}
