package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int multiply(int y) {
        return x * y;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperation(int y) {
        return sum(y) + minus(y) + multiply(y) + divide(y);
    }

    public static void main(String[] args) {
        int resSum = sum(10);
        System.out.println(resSum);
        int resMinus = minus(10);
        System.out.println(resMinus);
        Calculator calculator = new Calculator();
        int resMultiply = calculator.multiply(10);
        System.out.println(resMultiply);
        int resDivide = calculator.divide(10);
        System.out.println(resDivide);
        int resAll = calculator.sumAllOperation(10);
        System.out.println(resAll);
    }
}
