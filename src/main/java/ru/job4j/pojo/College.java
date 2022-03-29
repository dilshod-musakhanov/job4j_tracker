package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Dilshod");
        student.setSurname("Musakhanov");
        student.setGroup(3058);
        student.setJoined(new Date());

        System.out.println("Our student"
                + student.getName()
                + " " + student.getSurname()
                + " joined to Java group # "
                + student.getGroup()
                + " on " + student.getJoined());
    }
}
