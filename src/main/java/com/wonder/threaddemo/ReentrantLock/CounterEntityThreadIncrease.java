package com.wonder.threaddemo.ReentrantLock;

/**
 * 一个简单的线程，死循环的加数，模拟阻塞场景
 * @author jinrong.wang
 */
public class CounterEntityThreadIncrease implements Runnable {

    private CounterEntity counterEntity;

    public CounterEntityThreadIncrease(CounterEntity counterEntity) {
        this.counterEntity = counterEntity;
    }

    @Override
    public void run() {
        while (true) {
            this.counterEntity.increaseMethod(Thread.currentThread());
            try {
                Thread.sleep(10L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
