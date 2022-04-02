package ru.job4j.poly;

public class Bus implements Transport, Vehicle {

    private double cost = 1.7;

    @Override
    public void run() {
        System.out.println("Bus is running");
    }

    @Override
    public void passenger(int passenger) {
        if (passenger < 20) {
            System.out.println("This is a small bus");
            System.out.println("Maximum capacity of the bus is up to 20 passengers");
        } else if (passenger > 20 && passenger < 40) {
            System.out.println("This is a big bus");
            System.out.println("Maximum capacity of the bus is up to 40 passengers");
        } else {
            System.out.println("One bus us cannot take " + passenger + " passengers. Please take an additional vehicle.");
        }

    }

    @Override
    public double petrol(int petrolLitre) {
        return cost * petrolLitre;
    }

    @Override
    public void move() {
        System.out.println("Bus runs on the road");
    }

    @Override
    public void costComparison() {
        System.out.println("Bus ticket is cheaper than Train or Plane tickets");
    }
}
