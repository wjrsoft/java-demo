package com.wonder.threaddemo.writereadsyn;

public class TestThead {

    public static void main(String[] args) {
        BufferEntity bufferEntity = new BufferEntity();

        //写入线程
        ThreadWriter threadWriter = new ThreadWriter(bufferEntity);
        //读取线程
        ThreadRead threadRead = new ThreadRead(bufferEntity);

        Thread twThread = new Thread(threadWriter);
        Thread readThread = new Thread(threadRead);
        //写入线程长时间不释放锁
        twThread.start();
        try {
            //读取线程无法获取锁，也无法自动中断线程
            readThread.start();
            readThread.isInterrupted();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
