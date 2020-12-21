package com.wonder.designpatterns.subjectpatterns;

public class ConcreteObserver implements Observer {

    private String observerName;

    public ConcreteObserver(String observerName) {

        this.observerName = observerName;

    }

    @Override

    public void update() {

        System.out.println(observerName + "我要更新一下我的状态了......");
    }

}
