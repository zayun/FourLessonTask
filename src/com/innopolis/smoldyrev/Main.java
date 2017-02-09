package com.innopolis.smoldyrev;

public class Main {

    public static volatile int secFromStart = 0;

    public static final Object lock = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    synchronized (lock) {
                        lock.notifyAll();
                     }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        System.out.println(++secFromStart);
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}
                if (secFromStart%5==0) {
                    System.out.println("Прошло 5");
                }
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }}
                    if (secFromStart%7==0) {
                        System.out.println("Прошло 7");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
