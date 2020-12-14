package com.wonder.threaddemo.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Test_TryLockThread implements Runnable {
    private int i = 0;
    final ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>锁状态1：" + this.reentrantLock.isLocked());
            boolean islock1 = this.reentrantLock.tryLock();
            System.out.println(Thread.currentThread().getName() + ">>>>>>tryLock：" + islock1);
            if (islock1) {
                System.out.println(Thread.currentThread().getName() + ">>>>>>锁状态2：" + this.reentrantLock.isLocked());
                try {
                    System.out.println(Thread.currentThread().getName() + ">>>>>>>>打印：" + i);
                    i++;
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //锁一定要在监视器里才可以释放，如果没拿到锁这里释放是报错的
                    reentrantLock.unlock();
                    System.out.println(Thread.currentThread().getName() + ">>>>>>释放锁");
                    System.out.println(Thread.currentThread().getName() + ">>>>>>锁状态3：" + this.reentrantLock.isLocked());
                }
            } else {
                try {
                    System.out.println(Thread.currentThread().getName() + ">>>>>>没拿到锁睡眠一会儿");
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Test_TryLockThread test_tryLockThread = new Test_TryLockThread();
        Thread thread0 = new Thread(test_tryLockThread);
        Thread thread1 = new Thread(test_tryLockThread);
        thread0.start();
        thread1.start();
    }

}

