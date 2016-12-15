package com.platform.async;

class Consumer implements Runnable {
    private final Object lock;
    Consumer(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("Consumer");
        String threadName = Thread.currentThread().getName();
        Util.print(" --> " + threadName + " is Running !");
        synchronized (this.lock){
            try {
                // do sth.
                Util.print(threadName + " is doing something ... ");
                this.lock.wait();
                Util.print(threadName + " has been notified !");
            } catch (InterruptedException e) {
                Util.print(threadName + " is Interrupted !");
            }
            Util.print(threadName + " END. ");
        }
    }
}
