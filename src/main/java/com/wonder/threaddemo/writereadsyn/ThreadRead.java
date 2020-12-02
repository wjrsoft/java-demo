package com.wonder.threaddemo.writereadsyn;

public class ThreadRead implements  Runnable{
    private BufferEntity buffer ;

    public ThreadRead(BufferEntity buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        this.buffer.read();
    }
}