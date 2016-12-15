package com.platform.async;

class Producter implements Runnable {
    private final Object lock;

    Producter(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("Producter");
        Object obj = new Object();
        String threadName = Thread.currentThread().getName();
        Util.print(" --> " + threadName + " is Running !");
        synchronized (this.lock){
            // do something ...
            Util.print(threadName +" is doing something ...");
            this.lock.notifyAll();
            Util.print(threadName + " END.");
        }

    }
}
