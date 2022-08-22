package ru.job4j.tracker;

import java.util.List;

public class DeleteAllItems implements UserAction {
    private final Output out;

    public DeleteAllItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("===Delete all items===");
        List<Item> actualItems = tracker.findAll();
        for (Item item : actualItems) {
            tracker.delete(item.getId());
        }
        out.println("All items have been deleted");
        return true;
    }
}
