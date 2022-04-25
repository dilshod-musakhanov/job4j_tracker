package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void whenJobAscByName() {
        Comparator<Job> ascByName = new JobAscByName();
        int rsl = ascByName.compare(
                new Job("Make coffee", 1),
                new Job("Do homework", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenJobAscByPriority() {
        Comparator<Job> ascByName = new JobAscByPriority();
        int rsl = ascByName.compare(
                new Job("Take break", 1),
                new Job("Act busy", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenJobDescByName() {
        Comparator<Job> ascByName = new JobDescByName();
        int rsl = ascByName.compare(
                new Job("Make coffee", 1),
                new Job("Do homework", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenJobDescByPriority() {
        Comparator<Job> ascByName = new JobDescByPriority();
        int rsl = ascByName.compare(
                new Job("Take break", 1),
                new Job("Act busy", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameAndPriority() {
        Comparator<Job> cmpDescNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpDescNamePriority.compare(
                new Job("Work slowly", 0),
                new Job("Eat fast", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorBySameNamButPriority() {
        Comparator<Job> cmpBySameNamButPriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpBySameNamButPriority.compare(
                new Job("Work slowly", 0),
                new Job("Work slowly", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByNamButSamePriority() {
        Comparator<Job> cmpBySamePriorityButName = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpBySamePriorityButName.compare(
                new Job("Eat fast", 1),
                new Job("Work slowly", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}