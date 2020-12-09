package com.wonder.threaddemo.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        TestThreadRunable testThreadRunable = new TestThreadRunable();
        //直接加载10个线程，实际是一次最多只处理3个线程
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(testThreadRunable);
        }
        System.out.println(Thread.currentThread().getName()+">>>>>>加载10个线程完成");

    }
}

/**
 main>>>>>>加载10个线程完成
 pool-1-thread-1>>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果88888
 pool-1-thread-3>>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果88889
 pool-1-thread-2>>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果88890
 pool-1-thread-3>>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果88891
 pool-1-thread-1>>>>>> do something 每次算数处理需要固定一秒时间，运行处理业务结果88892
 **/