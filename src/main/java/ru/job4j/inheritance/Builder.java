package ru.job4j.inheritance;

public class Builder extends Engineer {
    private String mainInterest;

    private String goal;

    public Builder(String name, String surname, String education, String birthday, int yearsOfExperience, String mainInterest) {
        super(name, surname, education, birthday, yearsOfExperience);
        this.mainInterest = mainInterest;
    }

    public String mission(String goal) {
        this.goal = goal;
        return goal;
    }

    public static void main(String[] args) {
        Builder builder = new Builder("Elon", "Musk", "PHD", "28/07/1971", 30, "Space");
        System.out.println(builder.getName() + "'s main mission is " + builder.mission("to provide free internet to the world"));
    }
}
