package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        List<Address> rsl = profiles.stream()
                .map(profile -> profile.getAddress())
                .collect(Collectors.toList());
        return rsl;
    }
}
