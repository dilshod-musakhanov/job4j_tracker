package ru.job4j.inheritance;

public class Engineer extends Profession {
    private int yearsOfExperience;

    public Engineer(String name, String surname, String education, String birthday, int yearsOfExperience) {
        super(name, surname, education, birthday);
        this.yearsOfExperience = yearsOfExperience;
    }

    public String work(String project) {
        return project;
    }

    public static void main(String[] args) {
        Engineer engineer = new Engineer("James", "Bond", "MBA", "07/07/1977", 20);
        System.out.println(engineer.getName() + " works on " + engineer.work("SpyGame") + " Project");
    }
}
