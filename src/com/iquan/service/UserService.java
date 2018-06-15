package com.iquan.service;

import java.util.List;

import com.iquan.dao.UserDao;
import com.iquan.entity.User;

public class UserService {
private UserDao userDao;

public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
}
public User login(User user){
	return userDao.login(user);

}

public void add(String username,String password){
	userDao.add(username, password);
}
public List<User> findAll() {
	
	return userDao.findAll();
}
}
