package ru.job4j.tracker;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Item {
    @NonNull
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDateTime created = LocalDateTime.now();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("item id: %s, item name : %s, created: %s", id, name, FORMATTER.format(created));
    }

}