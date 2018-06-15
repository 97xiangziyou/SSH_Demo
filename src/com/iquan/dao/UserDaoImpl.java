package com.iquan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iquan.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@SuppressWarnings("all")
	public User login(User user) {
		// 调用方法得到模版对象
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username=? and password=?",
				user.getUsername(), user.getPassword());
		if (list != null && list.size() != 0) {
			User u = list.get(0);
			return u;
		} else {
			return null;
		}
	}

	// 增加用户的方法
	public void add(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		this.getHibernateTemplate().update(user);
	}

	// 修改客户的方法
	public void update(int id, String username, String password) {
		User user = this.getHibernateTemplate().get(User.class, id);
		user.setUsername(username);
		user.setPassword(password);
		this.getHibernateTemplate().update(user);
	}

	// 删除客户的方法
	public void delete(int id) {
		User user = this.getHibernateTemplate().get(User.class, id);
		this.getHibernateTemplate().delete(user);

	}

	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("from User");

	}

}
