package com.iquan.dao;

import java.util.List;

import com.iquan.entity.Visit;

public interface VisitDao {

	void add(Visit visit);

	List<Visit> findAll();

	Visit findOne(int vid);

	void delete(Visit v);

	void update(Visit v);
}
