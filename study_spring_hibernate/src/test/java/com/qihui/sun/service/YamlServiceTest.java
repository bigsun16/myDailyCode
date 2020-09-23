package com.qihui.sun.service;

import com.qihui.sun.bean.ServiceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class YamlServiceTest {
	@Autowired
	private YamlService serviceImpl;
	
	@Test
	public void testGetYmlFileData() {
		LinkedHashMap<String, Object> ymlFileData = serviceImpl.getYmlFileAllData(ServiceType.CHINA);
		System.out.println(ymlFileData);
	}

	@Test
	public void testGetAllYamlAttributes() {
		try {
			List<String> allYamlAttributes = serviceImpl.getAllYamlAttributes(ServiceType.AMERICA);
			System.out.println(allYamlAttributes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
