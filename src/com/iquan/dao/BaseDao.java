package com.iquan.dao;

import java.util.List;

/*
 * @author asus
 * 在接口后面使用泛型
 * 
 */
public interface BaseDao<T> {
//添加的方法
	void add(T t);
	//修改
	void update(T t);
	//删除
	void delete(T t);
	//根据ID查询
	T findOne(int id);
	//查询所有
	List<T> findAll();
}
