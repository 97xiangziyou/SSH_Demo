package com.iquan.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
//BaseDao接口的实现类
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class classzzType;
	public BaseDaoImpl(){
		//1.获取当前运行对象的class
		//比如运行custoemrDao实现类，得到customerDao实现类的class
		Class clazz=this.getClass();
		
		//2.获取运行类的父类的参数化类型
		Type type=clazz.getGenericSuperclass();
		
		//3.转换成子接口ParameterizedType
		ParameterizedType ptype=(ParameterizedType) type;
		
		//4.获取实际参数
		//比如Map<key,value>
		Type[] types=ptype.getActualTypeArguments();
		
		//5.把Type变成Class
		Class clazzParameter=(Class) types[0];
		this.classzzType=clazzParameter;
	}
		
	@Override
	public void add(T t) {
	this.getHibernateTemplate().save(t);
		
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@Override
	public T findOne(int id) {
	return (T) this.getHibernateTemplate().get(classzzType, id);
		
	}

	@Override
	public List<T> findAll() {
		//from后面一定记得要加一个空格字符 不然编译器报错 如fromcustomer  会连在一起无法查询
		return (List<T>) this.getHibernateTemplate().find("from "+classzzType.getSimpleName());
	}

}
