package com.qihui.sun.service;

import com.qihui.sun.bean.ServiceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
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
