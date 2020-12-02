package com.wonder.threaddemo.writereadsyn;

import java.util.LinkedList;
import java.util.Queue;

public class BufferEntity {
    public byte[] lock = new byte[0];
    Queue<String> qe = new LinkedList<String>();
    int i = 0;

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

    public void read() {
        synchronized (this.lock) {
            try {
                while (!qe.isEmpty()) {
                    System.out.println(qe.poll());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
