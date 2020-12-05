package com.wonder.threaddemo.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解释为什么ReentrantLock 是可重入锁
 * 让主线程连续获得method1/method2/method3方法里面的锁
 */
public class Test_Reentrant {
    static ReentrantLock reentrantLock=new ReentrantLock();
    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        reentrantLock.lock();
        try {
            System.out.println("方法1");
            method2();
        }finally {
            reentrantLock.unlock();
        }
    }

    private static void method2() {
        reentrantLock.lock();
        try {
            System.out.println("方法2");
            method3();
        }finally {
            reentrantLock.unlock();
        }
    }
    private static void method3() {
        reentrantLock.lock();
        try {
            System.out.println("方法3");
        }finally {
            reentrantLock.unlock();
        }
    }
}

