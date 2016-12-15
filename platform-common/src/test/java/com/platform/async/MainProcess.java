package com.platform.async;

import java.util.ArrayList;
import java.util.Vector;

public class MainProcess {
    public static void main(String[] args){

        final Object lock = new Object();
        final Object lock1 = new Object();
        // 1. Thread interrupt method.
        Thread t1 = new Thread(new Consumer(lock));
        Thread t2 = new Thread(new Producter(lock));
        Thread t3 = new Thread(new Consumer(lock));
        t1.start();
        t3.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        Vector<Integer> vector = new Vector<Integer>();
//        t1.interrupt();
    }
}
