package com.wonder.thread;

//依靠异常处理机制，3s后打断线程的休眠
public class ThreadTest01 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Processor1());
        t.setName("t");
        t.start();
//      Thread.sleep(3000);
//      t.interrupt();//打断t的休眠
    }
}

class Processor1 implements Runnable {
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}