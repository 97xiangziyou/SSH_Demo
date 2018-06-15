package com.iquan.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iquan.entity.LinkMan;
 
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao  {

//新增联系人
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
	}
	
//查询所有联系人
	public List<LinkMan> findAll() {
		List<LinkMan> list=(List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
		return list;
	}

//查询一个联系人根据ID
	public LinkMan findOne(int linkid) {
		LinkMan linkMan=this.getHibernateTemplate().get(LinkMan.class, linkid);
		return linkMan;
	}

//删除指定联系人的记录
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
		
	}

//修改某一个联系人的资料
	public void update(LinkMan c) {
		this.getHibernateTemplate().update(c);
		
	}


	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		/*String sql="from LinkMan where 1=1 ";
		List<Object> p=new ArrayList<Object>();
		if(linkMan.getLkmName()!=null&&!"".equals(linkMan.getLkmName())){
			sql+=" and lkmName=?";
			p.add(linkMan.getLkmName());
		}
		if(linkMan.getCustomer().getCid()>0&&linkMan.getCustomer().getCid()!=null){
			sql+=" and customer.cid=? ";
			p.add(linkMan.getCustomer().getCid());
		}
		
		return (List<LinkMan>) this.getHibernateTemplate().find(sql, p.toArray());*/
		String hql = "from LinkMan where 1=1 ";
		List<Object> p = new ArrayList<Object>();
		//判断条件值是否为空
		if(linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
			hql += " and lkmName=?";
			p.add(linkMan.getLkmName());
		}
		//判断是否选择客户
		if(linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
			//判断客户里面cid值
			hql += " and customer.cid=?";
			p.add(linkMan.getCustomer().getCid());
		}
		
		return (List<LinkMan>) this.getHibernateTemplate().find(hql, p.toArray());
	}

}
