package com.platform.async;

public class MainProcess {
    public static void main(String[] args){

        final Object lock = new Object();
        // 1. Thread interrupt method.
        Thread t1 = new Thread(new Consumer(lock));
        Thread t2 = new Thread(new Producter(lock));
        t1.start();
        t2.start();
//        t1.interrupt();
    }
}
