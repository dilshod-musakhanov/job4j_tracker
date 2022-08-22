package ru.job4j.tracker;

import java.util.List;

public class AddManyItems implements UserAction {
    private final Output out;

    public AddManyItems(Output out) {
        this.out = out;
    }


    @Override
    public String name() {
        return "Add many items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("===How many items you want to create?===");
        int quantity = input.askInt("Quantity: ");
        for (int i = 0; i < quantity; i++) {
                tracker.add(new Item(String.valueOf((char) i)));
        }
        out.println("New " + quantity + " items are added");
        return true;
    }
}
