package ru.job4j.collection;

import java.util.*;

public class JobSorter {
    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        Collections.sort(jobs, new JobDescByName().thenComparing(new JobDescByPriority()));
        System.out.println(jobs);
        System.out.println();
        List<Job> jobs2 = Arrays.asList(
                new Job("Fix bug", 2),
                new Job("Find bug", 1),
                new Job("Ignore bug", 4),
                new Job("Send bug to friend", 3)
        );
        Comparator<Job> comb = new JobDescByNameLn()
                .thenComparing(new JobDescByName())
                .thenComparing(new JobDescByPriority());
        Collections.sort(jobs2, comb);
        System.out.println(jobs2);
        System.out.println();
        List<Job> jobs3 = Arrays.asList(
                new Job("Fix bug", 2),
                new Job("Find bug", 1),
                new Job("Ignore bug", 4),
                new Job("Send bug to friend", 3)
        );
        Collections.sort(jobs3, new JobAscByName().thenComparing(new JobAscByPriority()));
        System.out.println(jobs3);

        List<Job> jobs4 = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        jobs.sort(new JobDescByName().thenComparing(new JobDescByPriority()));
        System.out.println(jobs4);

        Comparator<Job> compareName = Comparator.comparing(Job::getName);
        Comparator<Job> comparePriority = Comparator.comparingInt(Job::getPriority);
        Comparator<Job> combine = compareName.thenComparing(comparePriority);

        jobs4.sort(combine);
    }
}
