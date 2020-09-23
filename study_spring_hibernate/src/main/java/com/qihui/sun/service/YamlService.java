package com.qihui.sun.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.qihui.sun.bean.ServiceType;

public interface YamlService {
	public LinkedHashMap<String, Object> getYmlFileData(ServiceType type);
	public List<String> getAllYamlAttributes(ServiceType type);
	public LinkedHashMap<String, Object> getYmlFileAllData(ServiceType type);
}
