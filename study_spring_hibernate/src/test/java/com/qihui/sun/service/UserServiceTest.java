package com.qihui.sun.service;

import com.qihui.sun.bean.Gender;
import com.qihui.sun.bean.User;
import com.qihui.sun.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		userService.deleteUser(6L);
	}

	@Test
	public void testSaveUser() {
		User user = new User("萧峰", 33, Gender.male);
		userService.saveUser(user);
	}

	@Test
	public void testDeleteUser() {
		userService.deleteUser(3L);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setId(3L);
		user.setAge(33);
		user.setName("萧峰");
		user.setGender(Gender.male);
		userService.updateUser(user);
	}

	@Test
	public void testQueryUserById() {
		User user = userService.queryUserById(3L);
		System.out.println(user);
	}

	@Test
	public void testQueryAllUser() {
		System.out.println("www");
		List<User> queryAllUser = userService.queryAllUser();
		for (User user : queryAllUser) {
			System.out.println("user:::::" + user);
		}
	}
	@Test
	public void testQueryUserListByFiltersAndPage() {
		Map<String,Object> filters = new HashMap<>();
		filters.put("sex", "nan");
		List<User> queryAllUser = userService.queryUserListByFiltersAndPage(filters,1,10);
		if(queryAllUser==null) {
			return;
		}
		for (User user : queryAllUser) {
			System.out.println("user:::::"+user);
		}
	}
	
	@Test
	public void testUpdateUserByFilters() {
		Map<String,Object> filters = new HashMap<>();
		filters.put("age", "26");
		filters.put("name", "段誉");
		userService.updateUserByFilters(filters,3L);
	}
}
