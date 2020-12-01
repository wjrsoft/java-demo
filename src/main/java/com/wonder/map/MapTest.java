package com.wonder.map;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        List<String> list  =  new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry1 : entrySet) {
            System.out.println(entry1.getKey() + ":" + entry1.getValue());
        }



    }
}
