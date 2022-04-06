package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;

        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }

        if (rsl == -1) {
            throw new ElementNotFoundException("Element Not Found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            String[] str = {"one", "two", "three"};
            int index = indexOf(str, "two");
            System.out.println(index);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
