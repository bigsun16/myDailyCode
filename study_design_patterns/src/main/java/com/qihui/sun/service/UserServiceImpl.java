package com.qihui.sun.service;

import com.qihui.sun.beanFactoryPattern.BeanFactotyPattern;
import com.qihui.sun.dao.UserDao;

public class UserServiceImpl implements UserService {

	@Override
	public void show() {
		UserDao userDao = (UserDao) BeanFactotyPattern.getObject("userDao");
		userDao.show();
	}

	@Override
	public String testReturn(String string) {
		return string;
	}

}
