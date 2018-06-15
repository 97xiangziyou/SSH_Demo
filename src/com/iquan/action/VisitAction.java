package com.iquan.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.iquan.entity.Customer;
import com.iquan.entity.User;
import com.iquan.entity.Visit;
import com.iquan.service.CustomerService;
import com.iquan.service.LinkManService;
import com.iquan.service.UserService;
import com.iquan.service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {

	private Visit visit=new Visit();

	public Visit getModel() {
		return visit;
	}

	private VisitService visitService;

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private UserService userService;


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String addVisit() {

		visitService.add(visit);
		return "addVisit";
	}

	public String toAddPage() {
		// 查询所有客户
		List<Customer> listCustomer=customerService.findAll();
		//查询所有用户
		List<User> listUser=userService.findAll();
		//放到域对象里面
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("listCustomer", listCustomer);
		request.setAttribute("listUser", listUser);
		return "toAddPage";
		
	}
	
	
	public String toList(){
		
		List<Visit> list=visitService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	
	public String delete(){
		int vid=visit.getVid();
		Visit v=visitService.findOne(vid);
		visitService.delete(v);
		return "delete";
	}
	//新增一个修改页面即可完成.
	public String showVisit(){
		int vid=visit.getVid();
		Visit v=visitService.findOne(vid);	
		ServletActionContext.getRequest().setAttribute("visit",v);
		return "showVisit";
	}
	public String update(){
		Visit v = visit;
		visitService.update(v);
		return "update";
	}
}
