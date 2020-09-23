package com.qihui.sun.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXmlFileUtil {
	public static void main(String[] args) {
		Map<String, Set<String>> xmlAttributesByService = getXMLAttributesByService("container-ne");
		System.out.println(xmlAttributesByService);
	}
	public static Map<String, Set<String>> getXMLAttributesByService(String service) {
		Map<String, Set<String>> xmlAttributes = null;
		InputStream resourceAsStream = ReadXmlFileUtil.class.getClassLoader()
				.getResourceAsStream("friendlyNameView.xml");
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(resourceAsStream);
			Element rootElement = document.getRootElement();
			List<Element> mObjectElements = rootElement.elements("mObject");
			for (Element mObjectElement : mObjectElements) {
				String mObjectName = mObjectElement.attributeValue("name");
				if (mObjectName.equals(service)) {
					List<Element> sectionElements = mObjectElement.elements("section");
					xmlAttributes = new HashMap<>(sectionElements.size());
					for (Element sectionElement : sectionElements) {
						String sectionLabel = sectionElement.attributeValue("label");
						List<Element> attributeElements = sectionElement.elements("attribute");
						Set<String> attributes = new HashSet<>(attributeElements.size());
						for (Element attributeElement : attributeElements) {
							String attributeName = attributeElement.attributeValue("name");
							attributes.add(attributeName);
						}
						xmlAttributes.put(sectionLabel, attributes);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlAttributes;
	}
}
