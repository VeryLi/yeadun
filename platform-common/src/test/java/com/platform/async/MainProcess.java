package com.platform.async;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.LockSupport;

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
        CountDownLatch cdl = new CountDownLatch(10);
        cdl.countDown();
        LockSupport.park();
        Util.print(cdl.getCount() + "");

        RecursiveTask r = new RecursiveTask() {
            @Override
            protected Object compute() {
                return null;
            }
        };
        try {
            r.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        t1.interrupt();
    }
}
