package com.qihui.sun.lang.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimpleTest {
    private static final ThreadLocal<Map<String,Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("sun","qihui");
        threadLocal.set(map);
        Map<String, Object> threadLocalMap = threadLocal.get();
        Object value = threadLocalMap.get("sun");
        System.out.println(value);
    }
}
