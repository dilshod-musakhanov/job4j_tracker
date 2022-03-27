package ru.job4j.inheritance;

public class Doctor extends Profession {
    private boolean hasOwnClinic;

    public Doctor(String name, String surname, String education, String birthday, boolean hasOwnClinic) {
        super(name, surname, education, birthday);
        this.hasOwnClinic = hasOwnClinic;
    }

    public void consult() {
        System.out.println("Doctor is famous for her TVShow where she consults people on different health issues");
    }

    public static void main(String[] args) {
        Doctor doctor = new Doctor("Elena", "Malysheva", "PHD", "19/03/1961", true);
        System.out.println("All people in Post Soviet Countries know Doctor " + doctor.getName() + " " + doctor.getSurname());
        doctor.consult();
    }
}
