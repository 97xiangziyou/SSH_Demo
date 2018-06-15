package com.iquan.service;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.transaction.annotation.Transactional;

import com.iquan.dao.CustomerDao;
import com.iquan.dao.UserDao;
import com.iquan.entity.Customer;
import com.iquan.entity.Dict;
import com.iquan.entity.PageBean;

@Transactional
public class CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void add(Customer customer) {
		customerDao.add(customer);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public Customer findOne(int cid) {

		return customerDao.findOne(cid);
	}

	public void delete(Customer customer) {
		customerDao.delete(customer);

	}

	public void update(Customer c) {
		customerDao.update(c);
		
	}
	
	//封装分页数据到pagebean对象里面。
	public PageBean listpage(Integer currentPage) {
		//创建pagebean对象
		PageBean pageBean=new PageBean();
		//当前页
		pageBean.setCurrentPage(currentPage);
		//总记录数
		int totalCount=customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		//每页显示的记录数
		int pageSize=3;
		//总页数=总计路数除以每页显示的记录数
		//如果能整除便取值 不能整除则加1
		int totalPage=0;
		if(totalCount%pageSize==0){
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		//开始位置
		//每一页的开始位置等于当前页面数减去1再乘以页面长度
		int begin=(currentPage-1)*pageSize;
		//每页记录的List集合
		List<Customer> list=customerDao.findPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		List<Customer> list=customerDao.findCondition(customer);
		return list;
	}
	
	public List<Customer> findMoreCondition(Customer customer){
	return	customerDao.findMoreCondition(customer);
	}
	
	
	 public List<Dict> findCondition(Dict dict){
		return customerDao.findCondition(dict);
	}

	public List findCountSource() {
	return	customerDao.findCountSouce();
		
	}

	public List findCountLevel() {
	return	customerDao.findCountLevel();
		
	}


}
