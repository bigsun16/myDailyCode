package com.qihui.sun.beanFactoryPattern;

import com.qihui.sun.service.UserService;

public class TestBeanFactoryPattern {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			UserService userService = (UserService) BeanFactotyPattern.getObject("userService");
			System.out.println(userService);
//			userService.show();
		}
	}
}
