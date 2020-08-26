package com.wonder.thread1;

/**
 * 线程通信问题引出
 * 1、使用构造方法解决姓名和性别问题，但是还是没处理好存入和取出的问题
 */
class Producer implements Runnable {
    P q = null;

    public Producer(P q) {
        this.q = q;
    }

    public void run() {
        int i = 0;
        while (true) {
            if (i == 0) {
                q.set("张三", "男");
            } else {
                q.set("李四", "女");
            }
            i = (i + 1) % 2;
        }
    }
}


class Consumer implements Runnable {
    P q = null;

    public Consumer(P q) {
        this.q = q;
    }

    public void run() {
        while (true) {
            q.get();
        }
    }
}


class P {
    private String name = "李四";
    private String sex = "女";
    boolean bFull = false;

    public synchronized void set(String name, String sex) {
        if (bFull) {
            try {
                wait(); // 后来的线程要等待
            } catch (InterruptedException e) {
            }
        }
        this.name = name;
        try {
//            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.sex = sex;
        bFull = true;
        notify(); // 唤醒最先到达的线程
    }

    public synchronized void get() {
        if (!bFull) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(name + " ---->" + sex);
        bFull = false;
        notify();
    }
}


public class ThreadCommunation {
    public static void main(String[] args) {
        P q = new P();
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q)).start();
    }
}
