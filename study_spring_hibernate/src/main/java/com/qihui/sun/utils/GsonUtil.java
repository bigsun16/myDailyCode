package com.qihui.sun.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.qihui.sun.annotation.MyExceptField;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class GsonUtil {
	private GsonUtil() {
	}

	private static Gson gson = null;

	public static <T> T parserJson2Object(String content, Class<?> clazz) {
		T t = null;
		try {
			gson = new Gson();
			if (content != null) {
				JsonReader jreader = new JsonReader(new StringReader(content));
				jreader.setLenient(true);
				t = gson.fromJson(jreader, clazz);
			}
		} catch (JsonIOException | JsonSyntaxException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static String serializeSomeFieldsWithExposeAnnotation(Object obj) {
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(obj);
	}

	public static String serializeExceptSomeFields(Object obj, String fieldName) {
		ExclusionStrategy strategy = new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getName().equals(fieldName);
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		};
		gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
		return gson.toJson(obj);
	}

	public static String toJson(Object obj) {
		gson = new Gson();
		return gson.toJson(obj);
	}

	public static String serializeSomeFieldsWithoutMyExceptFieldAnnotation(Object obj) {
		ExclusionStrategy strategy = new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getAnnotation(MyExceptField.class) != null;
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		};
		gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
		return gson.toJson(obj);
	}

	public static String transferObject2Json(Object object) {
		gson = new Gson();
		return gson.toJson(object);
	}

	public static <T> T transferJson2Object(String json) {
		gson = new Gson();
		return gson.fromJson(json, new TypeToken<T>(){}.getType());
	}

	public static void main(String[] args) {
		String[] list = {"aa=2/bb=3","aa=2/cc=4"};
		Map<String,Object> map = new HashMap<>();
		map.put("aa",list);
		gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(map);
		System.out.println(json);
	}
}
