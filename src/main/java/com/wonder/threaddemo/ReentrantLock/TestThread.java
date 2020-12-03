package com.wonder.threaddemo.ReentrantLock;

public class TestThread {
    public static void main(String[] args) {
        CounterEntity counterEntity = new CounterEntity();

        ThreadIncrease threadIncrease = new ThreadIncrease(counterEntity);

        Thread thread1 = new Thread(threadIncrease);
        Thread thread2 = new Thread(threadIncrease);
        thread1.start();
        thread2.start();


    }
}
