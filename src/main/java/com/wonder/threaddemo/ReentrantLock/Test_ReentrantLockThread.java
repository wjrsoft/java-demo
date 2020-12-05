package com.wonder.threaddemo.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread.interrupt()和reentrantLock.lockInterruptibly() 可以打断其他线程占用的锁，可以不用一直阻塞
 * reentrantLock.lockInterruptibly();//如果没有线程占用锁，则获取锁。如果有线程占用锁则会阻塞
 * <p>
 * ;
 */
public class Test_ReentrantLockThread implements Runnable {
    //可重入锁
    public ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + ":锁状态0:" + reentrantLock.isLocked());

        try {
            //如果锁被占用，则打断线程
            if (reentrantLock.isLocked()) {
                Thread.currentThread().interrupt();
            }
            //如果没有线程占用锁，则获取锁。如果有线程占用锁则会阻塞
            reentrantLock.lockInterruptibly();
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + ": 打断锁异常InterruptedException");
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":锁状态1:" + reentrantLock.isLocked());

        //模拟长时间未释放锁，第二个线程进来阻塞场景
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ":进入finaly方法并释放锁1:" + reentrantLock.isLocked());
            if (reentrantLock.isLocked()) {
                reentrantLock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + ":进入finaly方法并释放锁2:" + reentrantLock.isLocked());
        }
    }


    public static void main(String[] args) {
        Test_ReentrantLockThread test_reentrantLockThread = new Test_ReentrantLockThread();
        Thread thread0 = new Thread(test_reentrantLockThread);
        Thread thread1 = new Thread(test_reentrantLockThread);

        thread0.start();
        System.out.println(thread0.getName() + ":启动线程");

        //休眠一会准备启动线程1
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
        System.out.println(thread1.getName() + ":启动线程");
    }
}
