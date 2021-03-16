package com.qihui.sun.util;

import com.qihui.sun.utils.jackson.JsonUtil;

import java.util.Map;
import java.util.TreeMap;

public class UtilsTest {

	public static void main(String[] args) {
		String filePath = "./testJsonData.json";
		Map<String,Long> data = new TreeMap<>();
		data.put("111111",111L);
		data.put("222222",222L);
		data.put("333333",333L);
		data.put("444444",444L);
		data.put("555555",555L);
		data.put("666666",666L);
		data.put("777777",777L);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.fluentPutAll(data);
//		String s = jsonObject.toJSONString();
		boolean filePath1 = JsonUtil.createJsonFile(data, filePath);
		System.out.println(filePath1);
	}


}
