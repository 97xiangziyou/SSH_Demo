package com.iquan.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.iquan.entity.Customer;
import com.iquan.entity.Dict;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

/*	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}*/

/*	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");

	}

	public Customer findOne(int cid) {

		return this.getHibernateTemplate().get(Customer.class, cid);
	}
*/
/*	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);

	}*/

/*	@Override
	public void update(Customer c) {
		this.getHibernateTemplate().update(c);
		
	}*/

	@Override
	public int findCount() {
		List<Object> list=(List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
		if(list.size()!=0&&list!=null){
			Object object=list.get(0);
			Long lobj=(Long)object;
			int count=lobj.intValue();
			return count;
		}
		return 0;
	}
	
	//分页查询操作
	public List<Customer> findPage(int begin, int pageSize) {
		//第一种 使用hibernate底层代码实现
		//先得到sessionFactory
	/*	SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		//设置分页信息
		Query query=session.createQuery("from Customer");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Customer> list=query.list();*/
		
		//第二种  使用离线对象和hibernateTemplate的方法实现   项目中用的比较多
		//1.创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		
		//调用hibernateTemplate的方法实现
		//第一个参数是离线对象
		//第二个参数是开始位置
		//第三个参数是每页记录数
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}


	public List<Customer> findCondition(Customer customer) {
		/*List<Customer> list=
		(List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		return list;*/
		//使用离线对象方式
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
		List<Customer> list = 
				(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}


	public List<Customer> findMoreCondition(Customer customer) {
		//使用hibernate模版的find方法实现
		//使用拼接语句
		String hql="from Customer where 1=1 ";
		List<Object> p=new ArrayList<Object>();
		//用List集合,如果值不为空，把值设置到List里面
		if(customer.getCustName()!=null&&!"".equals(customer.getCustName())){
			hql+=" and custName=?";
			p.add(customer.getCustName());
		}
		if(customer.getCustLevel()!=null&&!"".equals(customer.getCustLevel())){
			hql+=" and custLevel=?";
			p.add(customer.getCustLevel());
		}
		if(customer.getCustSource()!=null&&!"".equals(customer.getCustSource()))
		{
			hql+=" and custSource=?";
			p.add(customer.getCustSource());
		}
			return (List<Customer>)this.getHibernateTemplate().find(hql, p.toArray());
	}


	public List<Dict> findCondition(Dict dict) {
	return	(List<Dict>) this.getHibernateTemplate().find("from Dict");
		
	}


	public List findCountSouce() {
		String sql="SELECT COUNT(*) AS num,t.custSource FROM ssh_customer t GROUP BY t.custSource";
		//获取session对象
		Session session=this.getSessionFactory().getCurrentSession();
		//创建sqlquery对象
		SQLQuery sqlQuery=session.createSQLQuery(sql);
		//处理返回结果
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list=sqlQuery.list();
		return list;
	}


	public List findCountLevel() {
		//获取session对象
				Session session = this.getSessionFactory().getCurrentSession();
				//创建SQLQuery对象
				SQLQuery sqlQuery = session.createSQLQuery("SELECT c.num,d.dname FROM (SELECT COUNT(*) AS num,custLevel FROM ssh_customer GROUP BY custLevel) c , ssh_dict d WHERE c.custLevel=d.did");
				//得到结果
				//转换map结构
				sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
				List list = sqlQuery.list();
				return list;
	}

}
