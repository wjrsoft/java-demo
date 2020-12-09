package com.wonder.threaddemo.ThreadPoolDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadRunable implements Runnable {
    //数量
    private int num = 88888;

    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ">>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果" + this.num);
            num += 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
