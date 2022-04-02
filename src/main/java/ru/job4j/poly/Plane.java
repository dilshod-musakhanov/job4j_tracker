package ru.job4j.poly;

public class Plane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Plane flies in the air");
    }

    @Override
    public void costComparison() {
        System.out.println("Plane ticket is expensive comparing to Bus or Train tickets");
    }
}
