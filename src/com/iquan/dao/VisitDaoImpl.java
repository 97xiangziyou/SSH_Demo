package com.iquan.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iquan.entity.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);

	}

	public List<Visit> findAll() {
	return	(List<Visit>) this.getHibernateTemplate().find("from Visit");
		
	}


	public Visit findOne(int vid) {
	return	this.getHibernateTemplate().get(Visit.class,vid);
		
	}

	
	public void delete(Visit v) {
		this.getHibernateTemplate().delete(v);
		
	}

	
	public void update(Visit v) {
		this.getHibernateTemplate().update(v);
		
	}

}
