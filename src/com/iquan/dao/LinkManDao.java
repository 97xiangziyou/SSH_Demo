package com.iquan.dao;

import java.util.List;

import com.iquan.entity.Customer;
import com.iquan.entity.LinkMan;

public interface LinkManDao {
	public void add(LinkMan linkMan);

	public List<LinkMan> findAll();

	public LinkMan findOne(int linkid);

	public void delete(LinkMan linkMan);

	public void update(LinkMan c);
	public List<LinkMan> findMoreCondition(LinkMan linkMan);
}
