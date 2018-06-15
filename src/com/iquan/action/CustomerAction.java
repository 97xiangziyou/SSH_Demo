package com.iquan.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.iquan.entity.Customer;
import com.iquan.entity.Dict;
import com.iquan.entity.PageBean;
import com.iquan.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	public Customer getModel() {

		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 1 到添加页面
	public String toAddPage() {
		List<Dict> listDict=customerService.findCondition(customer.getDictCustLevel());
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		return "toAddPage";
	}

	// 2 添加的方法
	public String add() {
		customerService.add(customer);
		return "add";
	}

	// 3 客户列表的方法
	public String list() {
		List<Customer> list = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}

	public String delete() {
		// 使用模型驱动 获取cid的值
		// 只要实体类和表单数据相同 就可以用模型驱动直接获取 比如cid
		int cid = customer.getCid();
		Customer c = customerService.findOne(cid);
		customerService.delete(c);
		return "delete";
	}

	public String showCustomer() {
		
		int cid = customer.getCid();
		Customer c = customerService.findOne(cid);
		List<Dict> listDict=customerService.findCondition(customer.getDictCustLevel());
		ServletActionContext.getRequest().setAttribute("listDict", listDict);
		ServletActionContext.getRequest().setAttribute("customer", c);
		return "showCustomer";
	}

	public String update() {
		Customer c = customer;
		customerService.update(c);
		return "update";
	}
	
	//以下方法用于分页客户列表操作
	
	//使用属性封装获取
		private Integer currentPage;
		
	public Integer getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}

	public String listpage(){
		//调用service的方法实现封装
		PageBean pageBean = customerService.listpage(currentPage);
		//放到域对象里面
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		
		
		return "listpage";
	}
	
	//查询筛选操作
	//条件查询的方法
	public String listcondition() {
		//如果输入客户名称，根据客户名称查询
		//如果不输入任何内容，查询所有
		if(customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			//不为空
			List<Customer> list = customerService.findCondition(customer);
			ServletActionContext.getRequest().setAttribute("list", list);
		} else {
			//不输入任何内容，查询所有
//			list();
			List<Customer> list = customerService.findAll();
			ServletActionContext.getRequest().setAttribute("list", list);
		}
		return "listcondition";
	}
	public String findMoreCondition(){
		List<Customer> list=customerService.findMoreCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
		return "findMoreCondition";
	}
	
	public String select(){
		return "select";
	}
	
	public String listSource(){
		List list=customerService.findCountSource();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listSource";
	}
	
	public String listLevel(){
		List list=customerService.findCountLevel();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "listLevel";
	}
}


