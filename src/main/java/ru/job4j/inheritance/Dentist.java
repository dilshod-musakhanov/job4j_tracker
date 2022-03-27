package ru.job4j.inheritance;

public class Dentist extends Doctor {
    private boolean hasGoldenTooth;
    private String teeth;
    private int teethQuantity;

    public Dentist(String name, String surname, String education, String birthday, boolean hasOwnClinic, boolean hasGoldenTooth) {
        super(name, surname, education, birthday, hasOwnClinic);
        this.hasGoldenTooth = hasGoldenTooth;
    }

    public void problem(String damagedTeeth, int quantity) {
        this.teeth = damagedTeeth;
        this.teethQuantity = quantity;
    }

    public void heal() {
        System.out.println("Patience came with " + this.teethQuantity + " " + this.teeth + " and dentist healed them all");
    }

    public static void main(String[] args) {
        Dentist dentist = new Dentist("Zub", "Zubov", "PHD", "11/11/1961", true, true);
        dentist.problem("rotten teeth", 4);
        dentist.heal();
    }
}
