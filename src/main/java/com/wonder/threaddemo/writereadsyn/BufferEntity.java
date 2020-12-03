package com.wonder.threaddemo.writereadsyn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个类主要模拟synchronize的方法或者代码块，长时间占锁。
 * 其他线程调用同步方法长时间等待且无法自动中断
 * @author  jinrong.wang
 */
public class BufferEntity {
    public byte[] lock = new byte[0];
    Queue<String> qe = new LinkedList<String>();
    int i = 0;

    //模拟写入方法无线运行，使得其他线程进入这个对象所有同步方法都阻塞超长时间
    public void writer() {
        synchronized (this.lock) {
            while (true) {
                try {
                    Thread.sleep(1000L);
                    i++;
                    System.out.println(Thread.currentThread().getName()+">>>>>写入数据"+i);
                    qe.offer(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 如果内容部位空则读取
     */
    public void read() {
        synchronized (this.lock) {
            try {
                while (!qe.isEmpty()) {
                    System.out.println(qe.poll());
                }
                System.out.println("内容为空，睡眠一会");
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
