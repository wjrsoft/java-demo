package com.wonder.modification;

public class Man {
    Person person = new Person();


    public void method1(){
        person.num0=1;
        person.num1=2;
        System.out.println(person.num0);
        System.out.println(person.num1);
    }

    public static void main(String[] args) {
        Man man = new Man();
        man.method1();
    }
}
