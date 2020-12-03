package com.wonder.threaddemo.ReentrantLock;

public class ThreadIncrease implements Runnable {

    private CounterEntity counterEntity;

    public ThreadIncrease(CounterEntity counterEntity) {
        this.counterEntity = counterEntity;
    }

    @Override
    public void run() {
        while (true) {
            this.counterEntity.increaseMethod(Thread.currentThread());
            try {
                Thread.sleep(10L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
