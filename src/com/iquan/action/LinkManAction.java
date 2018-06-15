package com.iquan.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.iquan.entity.Customer;
import com.iquan.entity.LinkMan;
import com.iquan.service.CustomerService;
import com.iquan.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

private LinkManService linkManService;
private LinkMan linkMan = new LinkMan();

public void setLinkManService(LinkManService linkManService) {
	this.linkManService = linkManService;
}


public LinkMan getModel() {
	
	return linkMan;
}

private CustomerService customerService; 

public void setCustomerService(CustomerService customerService) {
	this.customerService = customerService;
}


// 1 到添加页面
public String toAddPage() {
	//调用客户service方法得到所有客户
	List<Customer> listCustomer=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	return "toAddPage";
}

// 2 添加的方法
public String add() throws Exception {
	//判断是否需要上传文件
	if(upload != null) {
		//写上传代码
		//在服务器文件夹里面创建文件
		File serverFile = new File("F:\\sshimg"+"/"+uploadFileName);
		//把上传文件复制到服务器文件里面
		FileUtils.copyFile(upload, serverFile);
	}
	
	linkManService.add(linkMan);
	return "add";
}
// 3 联系人列表的方法
public String list() {
	List<LinkMan> list = linkManService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "list";
}


public String delete() {
	// 使用模型驱动 获取cid的值
	// 只要实体类和表单数据相同 就可以用模型驱动直接获取 比如cid
	int linkid = linkMan.getLinkid();
	LinkMan c = linkManService.findOne(linkid);
	linkManService.delete(c);
	return "delete";
}

public String showlinkMan() {
	int linkid = linkMan.getLinkid();
	LinkMan c = linkManService.findOne(linkid);
	//需要获取所有客户的List集合。
	List<Customer> listCustomer=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	ServletActionContext.getRequest().setAttribute("linkman", c);
	return "showlinkMan";
}

public String update() {
	LinkMan c = linkMan;
	linkManService.update(c);
	return "update";
}

//上传文件功能
private File upload;
private String uploadFileName;

public File getUpload() {
	return upload;
}


public void setUpload(File upload) {
	this.upload = upload;
}


public String getUploadFileName() {
	return uploadFileName;
}


public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
}

public String toselectLinkMan(){
	//调用客户service方法得到所有客户
	List<Customer> list=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "toselectLinkMan";
}
	
public String moreCondition(){
	List<LinkMan> list=linkManService.findMoreCondition(linkMan);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
}

}
