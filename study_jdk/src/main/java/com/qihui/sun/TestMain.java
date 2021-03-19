package com.qihui.sun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class TestMain {
    public static void main(String[] args) {
        String value = null;
        Object value1 = Objects.requireNonNull(value, "value");
        System.out.println(value1);
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

    List<Map<String,Object>> values = new ArrayList<Map<String,Object>>(){
        {
            add(new HashMap<String,Object>() {{
                put("sourceFriendlyName", "name1");
                put("alarmSeverity", "主要");
            }});
            add(new HashMap<String,Object>() {{
                put("sourceFriendlyName", "name2");
                put("alarmSeverity", "警告");
            }});
            add(new HashMap<String,Object>() {{
                put("sourceFriendlyName", "name3");
                put("alarmSeverity", "严重");
            }});
            add(new HashMap<String,Object>() {{
                put("sourceFriendlyName", "name1");
                put("alarmSeverity", "次要");
            }});
            add(new HashMap<String,Object>() {{
                put("sourceFriendlyName", "name2");
                put("alarmSeverity", "次要");
            }});
        }
    };

    @Test
    public void testMap(){
        Map<String, List<Map<String, Object>>> collect = values.stream().
                collect(Collectors.groupingBy((map) -> String.valueOf(map.get("sourceFriendlyName"))));
        System.out.println(collect);
        System.out.println("=====================");
        Map<String,String> result = new HashMap<>();
        collect.forEach((key,value)->{
            boolean critical = value.stream().anyMatch((map) -> map.get("alarmSeverity").equals("严重"));
            boolean major = value.stream().anyMatch((map) -> map.get("alarmSeverity").equals("主要"));
            boolean minor = value.stream().anyMatch((map) -> map.get("alarmSeverity").equals("次要"));
            boolean warning = value.stream().anyMatch((map) -> map.get("alarmSeverity").equals("警告"));
            boolean indeterminate = value.stream().anyMatch((map) -> map.get("alarmSeverity").equals("提示"));
            if (critical){
                result.put(key,"Critical");
            } else if (major){
                result.put(key,"Major");
            } else if (minor){
                result.put(key,"Minor");
            } else if (warning){
                result.put(key,"Warning");
            } else if (indeterminate){
                result.put(key,"Indeterminate");
            }
        });
        System.out.println(result);
    }

    @Test
    public void testMap2(){
        Map<String,String> map = new HashMap<String,String>(){
            {
                put("11","aa");
                put("22","bb");
                put("33","cc");
                put("44","dd");
            }
        };
        System.out.println(map);
        map.forEach((key,value)->{
            if (key.equals("22") && value.equals("bb")){
                map.put(key,"haha");
            }
        });
        System.out.println(map);
    }

    @Test
    public void testMap3(){
        Map<String, String> dedicatedNetName = Collections.singletonMap("dedicatedNetName", "");
        System.out.println(dedicatedNetName);
        Map<String,Object> map = new HashMap<>();
        map.put("dedicatedNetName","");
        System.out.println(map);
    }

    @Test
    public void test4(){
        Map<String,Object> map = new HashMap<>();
        map.put("aa","");
        map.put("bb","");
        map.put("cc","");
        HashMap<Object, Object> collect = map.entrySet()
                .stream()
                .peek(entry -> entry.setValue(55))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        HashMap::new));
        System.out.println(collect);
    }
}
