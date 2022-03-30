package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String currentDateAndTime = formatter.format(item.getCreated());
        System.out.println("Current Date & Time: " + currentDateAndTime);
        Item task = new Item(9, "the last lesson");
        System.out.println(task);
    }
}
