package com.wonder.reflect;

import java.lang.reflect.Method;

public class reflectTest {
    public static void main(String[] args) throws Exception {
        String str = "hello";
        Method m = str.getClass().getMethod("toUpperCase");
        System.out.println(m.invoke(str));
    }
}
