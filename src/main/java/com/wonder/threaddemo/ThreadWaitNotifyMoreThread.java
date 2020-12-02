package com.wonder.threaddemo;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadWaitNotifyMoreThread implements Runnable {

    //构造些数据
    public Queue<String> qe = new LinkedList<String>();
    ////零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：
    // 生成零长度的byte[]对象只需3条操作码，而Object lock = new Object()则需要7行操作码。
    private final byte[] lock = new byte[0];

    //构造方法初始化些数据
    public ThreadWaitNotifyMoreThread(Queue<String> qe) {
        this.qe = qe;
    }

    //唤醒线程
    public void setNotify() {
        synchronized (this.lock) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>唤醒一个线程>>>>>>>>>>>>>>>>>>");
            this.lock.notify();
        }
    }

    //唤醒全部线程
    public void setNotifyAll() {
        synchronized (this.lock) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>唤醒所有线程>>>>>>>>>>>>>>>>>>");
            this.lock.notifyAll();
        }
    }


    public void setQueue(String data) {
        this.qe.offer(data);
    }

    public void getQueue() {
        try {
            Thread.sleep(100L);//刷太快了，休眠会儿
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + qe.poll());
    }


    @Override
    public void run() {
        while (true) {
            synchronized (this.lock) {
                try {
                    //当数据为空时，就等待吧
                    if (qe.isEmpty() == true) {
                        System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>开始等待");
                        this.lock.wait();
                    }
                    this.getQueue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 模拟多线程处理数据
     * 当队列中有数据时，唤醒所有线程一起工作
     * 当队列为空时，线程进入休眠
     * @param args
     */
    public static void main(String[] args) {
        Queue<String> qe = new LinkedList<String>();
        for (int i = 0; i < 30; i++) {
            qe.offer(String.valueOf(i));
        }

        ThreadWaitNotifyMoreThread threadMore = new ThreadWaitNotifyMoreThread(qe);
        Thread thread = new Thread(threadMore);
        Thread thread2 = new Thread(threadMore);
        Thread thread3 = new Thread(threadMore);

        thread.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(10000L);
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>开始休眠>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            if (qe.isEmpty() == true) {
                System.out.println(">>>>>队列数据读取完了，再补充点数据进去");
                for (int i = 0; i < 30; i++) {
                    qe.offer(String.valueOf(i));
                }
                threadMore.setNotifyAll();
            } else {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
