package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Error : " + active);
        System.out.println("Status : " + status);
        System.out.println("Message : " + message);
        System.out.println();
    }

    public static void main(String[] args) {
        Error error = new Error();
        error.printInfo();
        Error error1 = new Error(true, 404, "Not Found");
        error1.printInfo();
        Error error2 = new Error(true, 400, "Bad Request");
        error2.printInfo();
    }
}
