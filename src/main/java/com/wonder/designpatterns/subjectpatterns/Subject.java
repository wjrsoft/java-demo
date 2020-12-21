package com.wonder.designpatterns.subjectpatterns;

import java.util.ArrayList;
import java.util.Observer;

//https://www.jianshu.com/p/186a0041ac5b
public abstract class Subject {
    protected ArrayList observers = new ArrayList<>();

    //把观察者对象添加到观察者集合中
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //把观察者对象剔除到观察者集合中
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    //声明抽象方法

    public abstract void notifyObserver();
}