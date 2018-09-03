package com.slyone.java8;

public class TestRunnable {
    static Runnable r1 = () -> System.out.println("Hello World 1");

    static Runnable r2 = new Runnable() {
        public void run() {
            System.out.println("Hello World 2");
        }
    };

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        String e = "Hello";
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
        process(() -> System.out.println("This is awesome"));
        process(() -> System.out.println(e + ", this is awesome"));
    }
}
