package com.iquan.dao;

import java.util.List;

import com.iquan.entity.Customer;
import com.iquan.entity.Dict;

public interface CustomerDao extends BaseDao<Customer> {
//	public void add(Customer customer);

/*	public List<Customer> findAll();

	public Customer findOne(int cid);*/

//	public void delete(Customer customer);

//	public void update(Customer c);

	public int findCount();
	
	public List<Customer> findMoreCondition(Customer customer);

	public List<Customer> findPage(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);
	
	List<Dict> findCondition(Dict dict);

	public List findCountSouce();

	public List findCountLevel();

}
