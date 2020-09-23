package com.qihui.sun.util.collecion;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestCollectionSort {
    public static void main(String[] args) {
        testSortMap();
    }

    private static void testSortMap() {
        HashMap<String, Map<String, String>> map = new HashMap<>();

        HashMap<String, String> subMap1 = new HashMap<>();
        HashMap<String, String> subMap2 = new HashMap<>();
        HashMap<String, String> subMap3 = new HashMap<>();

        subMap1.put("11", "BB");
        subMap2.put("11", "CC");
        subMap3.put("11", "AA");

        map.put("aaa", subMap1);
        map.put("bbb", subMap2);
        map.put("ccc", subMap3);
        System.out.println(map);
        TreeMap<String, Map<String, String>> treeMap = new TreeMap<>((o1, o2) -> {
            String string1 = map.get(o1).get("11");
            String string2 = map.get(o2).get("11");
            int num = string1.compareTo(string2);
            return num == 0? 7: num ;
        });
        treeMap.putAll(map);
        System.out.println(treeMap);
    }
}
