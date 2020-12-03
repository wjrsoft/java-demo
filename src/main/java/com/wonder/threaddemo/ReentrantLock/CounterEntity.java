package com.wonder.threaddemo.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lock();是会阻塞程序的锁，直到成功获取锁
 *
 */

public class CounterEntity {
    private final Lock lock = new ReentrantLock();
    private volatile int count;

    public void increaseMethod(Thread td) {
        System.out.println(">>>>>>>"+td.getName()+"：等待中.......获取锁");
        lock.lock();
        System.out.println(">>>>>>>"+td.getName()+"：获取锁");

        try {
            count++;
            System.out.println(">>>>>>>"+td.getName()+":执行一段业务逻辑>>>>>"+count);
            //这里休眠段时间，在线程休眠时锁未释放，其他线程也无法获取到锁
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(">>>>>>>"+td.getName()+"：释放锁");

        }
    }

    public int getCount() {
        return count;
    }
}
