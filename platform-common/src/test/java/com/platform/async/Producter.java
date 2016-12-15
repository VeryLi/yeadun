package com.platform.async;

class Producter implements Runnable {
    private final Object lock;

    Producter(Object lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("Producter");
        String threadName = Thread.currentThread().getName();
        Util.print(" --> " + threadName + " is Running !");
        synchronized (this.lock){
            while(true){
                // do something ...
                Util.print(threadName +" is doing something ...");
                this.lock.notify();
//                break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            Util.print(threadName + " END.");
        }

    }
}
