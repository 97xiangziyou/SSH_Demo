package com.iquan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.iquan.dao.VisitDao;
import com.iquan.entity.Visit;
@Transactional
public class VisitService {
	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void add(Visit visit) {
		visitDao.add(visit);
	}

	public List<Visit> findAll() {
	List<Visit> list=visitDao.findAll();
		return list;
	}

	public Visit findOne(int vid) {
		return  visitDao.findOne(vid);
		
	}

	public void delete(Visit v) {
	visitDao.delete(v);
		
	}

	public void update(Visit v) {
		visitDao.update(v);
		
	}
}
