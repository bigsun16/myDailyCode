package com.qihui.sun.service;

import java.util.List;
import java.util.Map;

import com.qihui.sun.bean.User;
import com.qihui.sun.dao.UserDao;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User queryUserById(Long id) {
		User user = userDao.queryUserById(id);
		return user;
	}

	@Override
	public List<User> queryAllUser() {
		List<User> queryAllUser = userDao.queryAllUser();
		return queryAllUser;
	}

	@Override
	public List<User> queryUserListByFiltersAndPage(Map<String, Object> filters, int pageNum, int pageSize) {
		return userDao.queryUserListByFiltersAndPage(filters, pageNum, pageSize);
	}
	@Override
	public void updateUserByFilters(Map<String, Object> filters, Long id) {
		userDao.updateUserByFilters(filters, id);
	}
}
