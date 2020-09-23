package com.qihui.sun.service;

import java.util.List;
import java.util.Map;

import com.qihui.sun.bean.User;

public interface UserService {
	public void saveUser(User user);

	public void deleteUser(Long id);

	public void updateUser(User user);

	public User queryUserById(Long id);

	public List<User> queryAllUser();

	public List<User> queryUserListByFiltersAndPage(Map<String, Object> filters, int pageNum, int pageSize);
	
	public void updateUserByFilters(Map<String, Object> filters, Long id);
}
