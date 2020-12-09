package com.wonder.threaddemo.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestNewScheduledThreadPool {
    public static void main(String[] args) {
        //创建一个定长线程池，支持定时及周期性任务执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        TestThreadRunable testThreadRunable = new TestThreadRunable();
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.execute(testThreadRunable);
        }
        System.out.println(Thread.currentThread().getName()+">>>>>>加载10个线程完成");

    }
}
