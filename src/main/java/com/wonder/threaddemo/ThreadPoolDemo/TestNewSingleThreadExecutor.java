package com.wonder.threaddemo.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestNewSingleThreadExecutor {
    public static void main(String[] args) {
        //创建一个定长线程池，支持定时及周期性任务执行
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        TestThreadRunable testThreadRunable = new TestThreadRunable();
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(testThreadRunable);
        }
        System.out.println(Thread.currentThread().getName()+">>>>>>加载10个线程完成");

    }
}
