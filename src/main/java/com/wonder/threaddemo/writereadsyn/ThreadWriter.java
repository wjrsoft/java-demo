package com.wonder.threaddemo.writereadsyn;

public class ThreadWriter implements Runnable {

    private BufferEntity buffer;

    public ThreadWriter(BufferEntity buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        this.buffer.writer();
    }
}
