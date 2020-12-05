package com.wonder.threaddemo.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 这个类主要描述，reentrantLock是可以打断的
 * 1、main主线程对reentrantLock加锁
 * 2、t1线程打断锁捕获异常后，获取到锁
 */
public class Test_ReentrantlockInterruptibly {
    //锁放在常量里
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+":尝试获取锁:");
                reentrantLock.lockInterruptibly();//reentrantLock主线程已经加锁了，这里中断看看

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+": 打断锁异常InterruptedException");
                e.printStackTrace();
            }
            try {
                Boolean isGetLock = reentrantLock.isLocked();
                System.out.println(Thread.currentThread().getName()+":获取到锁:"+isGetLock);
            } finally {
                reentrantLock.unlock();
            }
        }, "t1");

        reentrantLock.lock();//开始先执行这句，主线程上锁，所以后来的t1就阻塞,如果
        System.out.println(">>>>"+Thread.currentThread().getName()+"：执行：reentrantLock.lock()");
        t1.start();
        System.out.println(">>>>启动线程 t1>>>>>>");
        Thread.sleep(1000L); ;//让主线程睡一会，t1拿到锁
        t1.interrupt();//打断t1
        System.out.println("t1.interrupt();打断执行");
    }
}





/**
 /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/bin/java -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:61586,suspend=y,server=n -javaagent:/Users/wangjinrong/Library/Caches/IntelliJIdea2019.3/captureAgent/debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/lib/tools.jar:/Users/wangjinrong/code/codepace/study/java-demo/target/classes:/Users/wangjinrong/code/maven/localRepository/com/alibaba/fastjson/1.2.7/fastjson-1.2.7.jar:/Users/wangjinrong/code/idea/IntelliJ IDEA.app/Contents/lib/idea_rt.jar" com.wonder.threaddemo.ReentrantLock.Test_ReentrantlockInterruptibly
 Connected to the target VM, address: '127.0.0.1:61586', transport: 'socket'
 >>>>main：执行：reentrantLock.lock()
 >>>>启动线程 t1>>>>>>
 t1:尝试获取锁:
 t1: 打断锁异常
 java.lang.InterruptedException
         at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireInterruptibly(AbstractQueuedSynchronizer.java:898)
         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireInterruptibly(AbstractQueuedSynchronizer.java:1222)
         at java.util.concurrent.locks.ReentrantLock.lockInterruptibly(ReentrantLock.java:335)
         at com.wonder.threaddemo.ReentrantLock.Test_ReentrantlockInterruptibly.lambda$main$0(Test_ReentrantlockInterruptibly.java:18)
         at java.lang.Thread.run(Thread.java:748)
         Exception in thread "t1" java.lang.IllegalMonitorStateException
         at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
         at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
 t1:获取到锁:true
     at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
     at com.wonder.threaddemo.ReentrantLock.Test_ReentrantlockInterruptibly.lambda$main$0(Test_ReentrantlockInterruptibly.java:28)
     at java.lang.Thread.run(Thread.java:748)
     Disconnected from the target VM, address: '127.0.0.1:61586', transport: 'socket'

     Process finished with exit code 0


 */