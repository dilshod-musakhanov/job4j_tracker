package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNlowerThanZeroCalc() {
        new Fact().calc(-1);
    }
}