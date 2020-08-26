package com.wonder.thread;

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
    boolean flag = true;
    int i=0;
    public synchronized void set(String name, String sex) {
        if(flag==false){
            this.name = name;
            this.sex = sex;
            flag =true;
        }

    }

    public synchronized void get() {

        if (flag == true) {
            System.out.println(this.name + " ---->" + this.sex+" --->i="+(++i));
            flag = false;
        }
    }
}



public class ThreadCommunation {
    public static void main(String[] args) {
        P q = new P();
        new Thread(new Producer(q)).start();
        new Thread(new Consumer(q)).start();
    }
}
