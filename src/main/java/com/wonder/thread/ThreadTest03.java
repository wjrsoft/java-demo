package com.wonder.thread;
//需求：更好的终止一个正在执行的线程

public class ThreadTest03 {
    public static void main(String[] args) throws InterruptedException {
        Processor5 p = new Processor5();
        Thread t = new Thread(p);
        t.setName("t5");
        t.start();
        Thread.sleep(5000);
        p.run = false;//终止
    }
}

class Processor5 implements Runnable {
    boolean run = true;// 利用Boolean来控制

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (run) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            } else {
                return;// 结束此方法
            }
        }
    }
}
