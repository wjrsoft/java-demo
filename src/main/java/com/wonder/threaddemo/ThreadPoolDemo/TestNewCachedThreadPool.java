package com.wonder.threaddemo.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于newCachedThreadPool线程池实现线程执行
 */
public class TestNewCachedThreadPool {

    //https://www.cnblogs.com/lanseyitai1224/p/7895652.html
    public static void main(String[] args) {
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //线程控制没处理一次需要一秒才可以结束，现起10个线程处理一起处理
        //testThreadRunable.num 初始数据是1，这里10个线程处理一定有并发问题,TestThreadRunable使用Lock.lock阻塞方式处理并发问题
        TestThreadRunable testThreadRunable = new TestThreadRunable();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(testThreadRunable);
        }
    }
}
