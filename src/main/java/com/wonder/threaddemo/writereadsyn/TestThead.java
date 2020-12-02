package com.wonder.threaddemo.writereadsyn;

public class TestThead {

    public static void main(String[] args) {
        BufferEntity bufferEntity = new BufferEntity();
        ThreadWriter threadWriter = new ThreadWriter(bufferEntity);
        ThreadRead threadRead = new ThreadRead(bufferEntity);
        Thread twThread = new Thread(threadWriter);
        Thread readThread = new Thread(threadRead);
        twThread.start();
        try {
            readThread.start();
            readThread.isInterrupted();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
