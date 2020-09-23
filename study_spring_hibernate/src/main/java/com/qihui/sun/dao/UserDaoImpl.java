package com.qihui.sun.dao;

import com.qihui.sun.bean.Gender;
import com.qihui.sun.bean.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void saveUser(User user) {
		try {
			hibernateTemplate.save(user);
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
		/*
		 * Session session = null; try { session = HibernateUtil.getCurrentSession();
		 * Transaction tx = session.getTransaction(); tx.begin(); session.save(user);
		 * tx.commit(); } catch (Exception e) { e.printStackTrace(); } finally {
		 * session.close(); }
		 */
	}

	@Override
	public void deleteUser(Long id) {
		try {
			User user = new User();
			user.setId(id);
			hibernateTemplate.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			System.out.println("update user start....."+user);
			hibernateTemplate.saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User queryUserById(Long id) {
		try {
			return hibernateTemplate.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("all")
	@Override
	public List<User> queryAllUser() {
		try {
			List<User> userList = (List<User>) hibernateTemplate.find("from User");
			return userList;
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<User> queryUserListByFiltersAndPage(Map<String, Object> filters, int pageNum, int pageSize) {
		return null;
	}

	@Override
	public void updateUserByFilters(Map<String, Object> filters, Long id) {
		try {
			User user = queryUserById(id);
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				switch (key) {
				case "name":
					user.setName(String.valueOf(value));
					break;
				case "age":
					user.setAge(Integer.valueOf(String.valueOf(value)));
					break;
				case "gender":
					user.setGender((Gender) value);
					break;
				}
			}
			updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
