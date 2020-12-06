package com.wonder.string;

/**
 * String 对象不可变
 */
public class StringTest {

    public static String StringAdd(String s) {
        s += "bbb";
        return s;
    }
    public static void main(String[] args) {

        String s = "aaa";
        StringAdd(s);
        System.out.println(s);
        System.out.println(StringAdd(s));

    }
}

