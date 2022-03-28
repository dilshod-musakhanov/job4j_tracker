package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book gaming = new Book("Create your own game", 100);
        Book coding = new Book("Clean code", 150);
        Book design = new Book("Photoshop in 100 steps", 200);
        Book programming = new Book("Java Advanced", 350);

        Book[] books = new Book[4];
        books[0] = gaming;
        books[1] = coding;
        books[2] = design;
        books[3] = programming;

        for (int i = 0; i < books.length; i++) {
            Book bk = books[i];
            System.out.println(bk.getName() + " - " + bk.getPage() + " pages");
        }

        System.out.println("***");

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        for (int i = 0; i < books.length; i++) {
            Book bk = books[i];
            System.out.println(bk.getName() + " - " + bk.getPage() + " pages");
        }

        System.out.println("***");

        for (Book bk : books) {
            if (bk.equals(coding)) {
                System.out.println(bk.getName());
                break;
            }
        }
    }
}
