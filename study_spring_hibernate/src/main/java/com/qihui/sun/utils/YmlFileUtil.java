package com.qihui.sun.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class YmlFileUtil {
    private YmlFileUtil() {
    }
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final List<String> fileNameList = new ArrayList<>();
    private static Yaml yaml;
    private static DumperOptions dumperOptions;
    static {
        dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
//        dumperOptions.setPrettyFlow(false);
    }

    static {
        String path = YmlFileUtil.class.getClassLoader().getResource("yml/").getPath();
//		System.out.println("path=="+path);
        File dir = new File(path);
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String name = file.getName();
                fileNameList.add(name);
            }
        }
    }

    private static String getTargetYamlFileName(String service) {
        String targetFileName = null;
        for (String fileName : fileNameList) {
            if (fileName.startsWith(service)) {
                targetFileName = fileName;
                break;
            }
        }
        if (targetFileName == null) {
            throw new RuntimeException("no yml file found of the type...");
        }
        return targetFileName;
    }

    public static Set<String> getAllYamlAttributesByType(String type, String fileName) {
        rwLock.readLock().lock();
        try {
            LinkedHashMap<String, Object> resultMap = loadData(type, fileName);
            if (resultMap!=null){
                LinkedHashMap<String, Object> typeMap = (LinkedHashMap<String, Object>) resultMap.get(type);
                if (typeMap!=null){
                    return typeMap.keySet();
                }
            }
        } finally {
            rwLock.readLock().unlock();
        }
        return null;
    }

    public static LinkedHashMap<String, Object> getYamlDataByType(String type, String fileName) {
        rwLock.readLock().lock();
        try(InputStream resourceAsStream = new FileInputStream(new File(fileName))) {
            yaml = new Yaml();
            Iterable<Object> loadAll = yaml.loadAll(resourceAsStream);
            for (Object object : loadAll) {
                LinkedHashMap<String, Object> resultMap = (LinkedHashMap<String, Object>) object;
                if (resultMap.containsKey(type)) {
                    return resultMap;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return null;
    }

    public static LinkedHashMap<String, Object> loadData(String type, String fileName) {
        rwLock.readLock().lock();
        Yaml yml = new Yaml();
        try (InputStream is = new FileInputStream(new File(fileName))){
            LinkedHashMap<String, Object> allData = yml.load(is);
            if (type == null || type.equals("")) {
                return allData;
            }
            if (allData.containsKey(type)) {
                LinkedHashMap<String, Object> returnMap = new LinkedHashMap<>();
                returnMap.put(type, allData.get(type));
                return returnMap;
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return null;
    }

    public static void writeAttributesByType(String fileName, String serviceType, Object attributeMap) {
        rwLock.writeLock().lock();
        System.out.println("write start....");
        try (InputStream inputStream = new FileInputStream(new File(fileName))){
            System.out.println("inputStream："+inputStream);
            yaml = new Yaml(dumperOptions);
            LinkedHashMap<String, Object> allData = yaml.load(inputStream);
            System.out.println(allData);
            allData.put(serviceType, attributeMap);
            FileWriter fileWriter = new FileWriter(new File(fileName));
            yaml.dump(allData, fileWriter);
            System.out.println("write over....");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("release write lock...");
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
//        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
//        LinkedHashMap<String, Object> person = new LinkedHashMap<>();
//        person.put("name", "qihui");
//        person.put("age", 28);
//        map.put("qihui", person);
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
//		        writeAttributesByType("D:\\myDailyDemo\\test_spring_hibernate\\src\\main\\resources\\yml\\test.yml","PEOPLE",map.clone());
                LinkedHashMap<String, Object> china = loadData("china", "C:\\myDailyDemo\\test_spring_hibernate\\src\\main\\resources\\yml\\test.yml");
                System.out.println(china );
            });
            executorService.execute(() -> {
                LinkedHashMap<String, Object> people = loadData("people", "C:\\myDailyDemo\\test_spring_hibernate\\src\\main\\resources\\yml\\test.yml");
                System.out.println(people );
            });
        }
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

}