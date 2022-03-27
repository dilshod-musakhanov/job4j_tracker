package ru.job4j.inheritance;

public class Programmer extends Engineer {
    private boolean workFromHome;

    public Programmer(String name, String surname, String education, String birthday, int yearsOfExperience, boolean workFromHome) {
        super(name, surname, education, birthday, yearsOfExperience);
        this.workFromHome = workFromHome;
    }

    public String develop(String program) {
        return program;
    }

    public static void main(String[] args) {
        Programmer programmer = new Programmer("Bill", "Gates", "School", "28/10/1955", 40, true);
        System.out.println(programmer.getName() + " keeps developing " + programmer.develop("IBM " + "products"));
    }
}
