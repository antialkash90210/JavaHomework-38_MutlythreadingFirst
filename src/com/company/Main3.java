package com.company;

class ConsolePrinter implements Runnable{
    private String threadName;

    public ConsolePrinter(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName+": "+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Main3 {
    public static void main(String[] args) throws Exception {
        Thread th1 = new Thread(new ConsolePrinter("th1"));
        Thread th2 = new Thread(new ConsolePrinter("th2"));
        Thread th3 = new Thread(new ConsolePrinter("th3"));

        th1.start();
        th2.start();
        th3.start();
    }
}