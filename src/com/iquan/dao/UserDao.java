package com.iquan.dao;

import java.util.List;

import com.iquan.entity.User;

public interface UserDao {
public User login(User user);
public void add(String username,String password);
public void update(int id,String username,String password);
public void delete(int id);
public List<User> findAll();
}
