package com.qihui.sun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        testGson();
    }

    private static void testGson() {
        Map<String, Object> map = new HashMap<>();
        String[] list = {"/elementNr=5/rackNr=1/subrackNr=1/slotNr=13", "/elementNr=5/rackNr=1/subrackNr=1/slotNr=13/portNr=5"};
        map.put("msgkey", "cutover.validation.destLtIsNotEmpty");
        map.put("msgargs", list);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(map);
        System.out.println(json);
        System.out.println(gson.fromJson(json, Map.class));

        Map<String,Object> map1 = gson.fromJson(json, Map.class);
        ArrayList<String> argss = (ArrayList<String>) map1.get("msgargs");
        System.out.println(argss);
    }
}
