package ru.job4j.lombok;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder(builderMethodName = "of")
public class Permission {
    private int id;
    private String name;

    @Singular("rules")
    private List<String> rules;

    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("ADMIN")
                .rules("add")
                .rules("read")
                .rules("delete")
                .rules("delete")
                .build();
        System.out.println(permission);
    }
}
