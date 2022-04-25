package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenItemAscByName() {
        List<Item> items = Arrays.asList(
                new Item("Book"),
                new Item("Dinner"),
                new Item("Animal"),
                new Item("Chess")
        );
        List<Item> expected = Arrays.asList(
                new Item("Animal"),
                new Item("Book"),
                new Item("Chess"),
                new Item("Dinner")
        );
        Collections.sort(items, new ItemAscByName());
        assertThat(items, is(expected));

    }

    @Test
    public void whenItemDescByName() {
        List<Item> items = Arrays.asList(
                new Item("Book"),
                new Item("Dinner"),
                new Item("Animal"),
                new Item("Chess")
        );
        List<Item> expected = Arrays.asList(
                new Item("Dinner"),
                new Item("Chess"),
                new Item("Book"),
                new Item("Animal")
        );
        Collections.sort(items, new ItemDescByName());
        assertThat(items, is(expected));

    }
}