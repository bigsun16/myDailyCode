package com.qihui.sun.beanFactoryPattern;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
/**
 * single instance
 * @author qihuis
 *
 */
public class BeanFactotyPattern {
	private static Properties prop;
	private static Map<String, Object> beanMap = new HashMap<>();
	static {
		prop = new Properties();
		InputStream is = BeanFactotyPattern.class.getClassLoader().getResourceAsStream("beans.properties");
		try {
			prop.load(is);
			Set<Entry<Object, Object>> entrySet = prop.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				String beanKey = (String) entry.getKey();
				String beanName = (String) entry.getValue();
				Object bean = Class.forName(beanName).newInstance();
				beanMap.put(beanKey, bean);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Object getObject(String beanName) {
		if (beanName != null) {
			return beanMap.get(beanName);
		}
		return null;
	}
}
