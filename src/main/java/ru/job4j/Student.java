package ru.job4j;

public class Student {
    public void music(String lyrics) {
        System.out.println("I can sing : " + lyrics);
    }

    public void song() {
        System.out.println("I believe I can fly");
    }

    public static void main(String[] args) {
        Student petya = new Student();
        String song = "I believe I can fly";
        petya.music(song);
        petya.song();
    }
}
