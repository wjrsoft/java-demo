package com.wonder.thread;


public class ThreadDemo9_4 {
//    public static void main(String[] args) {
//        TestThread t = new TestThread();
//        // 启动了四个线程，并实现了资源共享的目的
//        new Thread(t).start();
//        new Thread(t).start();
//        new Thread(t).start();
//        new Thread(t).start();
//    }

    public static void main(String[] args) {
        TestThread t = new TestThread();
        TestThread t1 = new TestThread();
        // 启动了四个线程，并实现了资源共享的目的
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t1).start();
        new Thread(t1).start();
    }
}

class TestThread implements Runnable {
    private int tickets = 10;
    public void run() {
        while (true) {
            if (tickets > 0) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "出售票" + tickets--);
            }

        }
    }

}
