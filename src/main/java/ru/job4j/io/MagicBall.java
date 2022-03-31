package ru.job4j.io;

import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public String randomNumber() {
        int answer = new Random().nextInt(3);
        if (answer == 0) {
            return  "Да.";
        } else if (answer == 1) {
            return "Нет.";
        } else {
            return "Может быть.";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Я великий Оракул. Что ты хочешь узнать? ");
        String question = input.nextLine();
        MagicBall magicBall = new MagicBall();
        System.out.println(question + " мой ответ: " + magicBall.randomNumber());
    }
}
