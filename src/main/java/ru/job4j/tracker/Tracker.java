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

    public int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(index + 1);
            items[index] = item;

            return true;
        }
        return false;
    }
}