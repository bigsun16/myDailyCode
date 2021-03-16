package com.qihui.sun.utils.jackson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.qihui.sun.utils.GsonUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonUtil {
    public static boolean createJsonFile(Object jsonData, String filePath) {
//        String prettyJsonString = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullNumberAsZero);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonData.toString());
        String prettyJsonString = gson.toJson(je);
        boolean flag = true;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            write.write(prettyJsonString);
            write.flush();
            write.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                builder.append(line);
            }
            reader.close();
            System.out.println(builder);
            Object data = GsonUtil.parserJson2Object(builder.toString(), Map.class);
            System.out.println(data);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
