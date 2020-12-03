package com.wonder.threaddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要是 wait 和 notify的使用方法
 * wait和notify使用一定在synchronized方法或者代码块，且监视对象(lock/object)必须相同的
 * @author  jinrong.wang
 */
public class ThreadWaitNotify implements Runnable {
    //byte[] lock 是对象锁，最小开销的写法
    private final byte[] lock = new byte[0];
    //对象是否需要阻塞的一个判断表示(比如说集合没数据了，可以先阻塞下)
    private boolean waitFlag = false;

    List<String> list = new ArrayList<String>();

    /**
     * 构造方法传入数据
     *
     * @param list
     * @param waitFlag
     */
    public ThreadWaitNotify(List<String> list, boolean waitFlag) {
        this.list = list;
        this.waitFlag = waitFlag;
    }

    public void setWaitFlag(boolean waitFlag) {
        this.waitFlag = waitFlag;
    }

    //唤醒方法
    public void setNotify() {
        synchronized (this.lock) {
            System.out.println(Thread.currentThread().getName() + "进入notify方法，唤醒正在阻塞的线程");
            this.lock.notify();
        }
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Thread.currentThread().getName() + "打印：" + list.get(i));
        }
    }

    @Override
    public void run() {
        try {
            synchronized (this.lock) {
                if (this.waitFlag == true) {
                    System.out.println(Thread.currentThread().getName() + "进入wait方法");
                    this.lock.wait();
                    System.out.println(Thread.currentThread().getName() + "跳出wait方法");
                }
                //处理数据
                printList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //初始化一些测试数据
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }

        //ThreadWaitNotify 初始化对象，设置为阻塞状态
        ThreadWaitNotify threadWaitNotify = new ThreadWaitNotify(list, true);
        Thread thread = new Thread(threadWaitNotify);
        // NEW 线程新建状态
        System.out.println(Thread.currentThread().getName() +"线程监视器状态1:"+thread.getState());
        thread.start();
        // RUNNABLE 运行中  这里是运行中是因为thread.start()直接把线程变成运行状态，当调用wait方法时才会阻塞
        System.out.println(Thread.currentThread().getName() +"线程监视器状态2:"+thread.getState());


        //唤醒正在阻塞的线程，因为线程是异步的，这里先让程序休眠100毫秒
        try {
            Thread.sleep(100L);
            // WAITING 阻塞，因为代码里调了wait方法，所以线程已经阻塞了
            System.out.println(Thread.currentThread().getName() +"线程监视器状态3:"+thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //唤醒,这时被阻塞的线程将会开始处理数据
        threadWaitNotify.setNotify();
        //BLOCKED 销毁
        System.out.println(Thread.currentThread().getName() +"线程监视器状态4:"+thread.getState());
        thread.start();//因为线程已经销毁了，这里执行会报错
    }
}
