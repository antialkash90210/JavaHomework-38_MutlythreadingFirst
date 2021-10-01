package com.company;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable done");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread done");
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("main start");

        MyRunnable mr1 = new MyRunnable();
        Thread th1 = new Thread(mr1);
        th1.start();

        MyThread th2 = new MyThread();
        th2.start();

        Thread th3 = new Thread(() -> {
            System.out.println("lymbda done!");
        });
        th3.start();

        System.out.println("main finish");
    }
}