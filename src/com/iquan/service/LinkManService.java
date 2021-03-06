package com.iquan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.iquan.dao.LinkManDao;
import com.iquan.entity.LinkMan;
@Transactional
public class LinkManService {
private LinkManDao linkManDao;

public void setLinkManDao(LinkManDao linkManDao) {
	this.linkManDao = linkManDao;
}
	
public void add(LinkMan linkMan){
	linkManDao.add(linkMan);
}


public List<LinkMan> findAll(){
	return linkManDao.findAll();
}

public LinkMan findOne(int linkid){
	return linkManDao.findOne(linkid);
}


public void delete(LinkMan linkMan){
	linkManDao.delete(linkMan);
}


public void update(LinkMan c){
	linkManDao.update(c);
}

public List<LinkMan> findMoreCondition(LinkMan linkMan){
	return	linkManDao.findMoreCondition(linkMan);
}
}
