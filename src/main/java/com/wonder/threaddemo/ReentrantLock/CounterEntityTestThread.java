package com.wonder.threaddemo.ReentrantLock;

/**
 * 启动两个线程，模拟并发操作，通过lock.lock控制并发并阻塞
 */
public class CounterEntityTestThread {
    public static void main(String[] args) {

        CounterEntity counterEntity = new CounterEntity();

        CounterEntityThreadIncrease threadIncrease = new CounterEntityThreadIncrease(counterEntity);
        //启动两个线程，模拟并发操作，通过lock.lock控制并发并阻塞
        Thread thread1 = new Thread(threadIncrease);
        Thread thread2 = new Thread(threadIncrease);
        thread1.start();
        thread2.start();


    }
}
