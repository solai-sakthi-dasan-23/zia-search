package com.ziasearch;

public class JavaGrid {

    public static void print(String string) throws InterruptedException {
        for(int i=0; i<string.length(); i++) {
            System.out.print(string.charAt(i));
            Thread.sleep(50);
        }
    }

    public static void print(String string, int interval) throws InterruptedException {
        for(int i=0; i<string.length(); i++) {
            System.out.print(string.charAt(i));
            Thread.sleep(interval);
        }
    }

    public static void println(String string) throws InterruptedException {
        for(int i=0; i<string.length(); i++) {
            System.out.print(string.charAt(i));
            Thread.sleep(20);
        }
        System.out.println();
    }

    public static void println(String string, int interval) throws InterruptedException {
        for(int i=0; i<string.length(); i++) {
            System.out.print(string.charAt(i));
            Thread.sleep(interval);
        }
        System.out.println();
    }

    public static void wait(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
