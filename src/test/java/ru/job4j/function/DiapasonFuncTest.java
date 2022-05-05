package ru.job4j.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DiapasonFuncTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = DiapasonFunc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadFunctionThenQuadResults() {
        List<Double> result = DiapasonFunc.diapason(4, 7, x -> x * x + 1);
        List<Double> expected = Arrays.asList(17D, 26D, 37D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = DiapasonFunc.diapason(2, 5, x -> Math.pow(x, 3));
        List<Double> expected = Arrays.asList(8D, 27D, 64D);
        assertThat(result, is(expected));
    }
}