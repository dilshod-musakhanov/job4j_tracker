package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] result = new Item[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
                result[count] = items[i];
                count++;
        }
        return Arrays.copyOf(result, count);
    }

    public Item[] findByName(String key) {
        Item[] newArr = new Item[items.length];
        int size = 0;
        for (int i = 0; i < this.items.length; i++) {
            if (items[i] != null) {
                if (key.equals(items[i].getName())) {
                    newArr[size] = items[i];
                    size++;
                }
            }
        }
        newArr = Arrays.copyOf(newArr, size);
        return newArr;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}