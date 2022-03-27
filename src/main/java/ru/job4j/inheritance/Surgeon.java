package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private boolean hasSuperPower;

    public Surgeon(String name, String surname, String education, String birthday, boolean hasOwnClinic, boolean hasSuperPower) {
        super(name, surname, education, birthday, hasOwnClinic);
        this.hasSuperPower = hasSuperPower;
    }

    public String specialize(String speciality) {
        return speciality;
    }

    public static void main(String[] args) {
        Surgeon surgeon = new Surgeon("Doctor", "Strange", "PHD", "19/07/1976", true, true);
        System.out.println(surgeon.getName() + " "  + surgeon.getSurname() + " has mastered how to heal people" + surgeon.specialize(" with power of mind"));
   }
}
