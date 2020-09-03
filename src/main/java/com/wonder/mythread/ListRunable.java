package com.wonder.mythread;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过构造方法传数据到线程类里
 */
public class ListRunable implements Runnable {


    public List<Integer> list = new ArrayList<Integer>();

    public ListRunable(List<Integer> paramList) {
        this.list = paramList;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Thread.currentThread().getName()+":"+list.get(i));
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            test1();
        }

    }

    public  static void test1() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        ListRunable listRunable = new ListRunable(list);
        Thread t = new Thread(listRunable);
        System.out.println("======================"+t.getName()+"=====线程任务开始处理=========================");
        t.start();
        System.out.println("======================"+t.getName()+"=====线程任务结束处理=========================");
    }
}
