package ru.job4j.poly;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Train moves on the railway");
    }

    @Override
    public void costComparison() {
        System.out.println("Train ticket is higher than Bus ticket but cheaper than Plane ticket");
    }
}
