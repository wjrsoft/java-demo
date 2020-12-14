package com.wonder.string;

public class StringEqualMethod {

    public static void main(String[] args) {
        //栈中开辟一块空间存放引用 str1，str2 指向池中 String 常量"abc"
        String str1 = "abc";
        String str2 = "abc";
        //new 堆中开辟一个新空间存放abc
        String str3 = new String("abc");
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
    }
}
