package com.iquan.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.iquan.entity.User;
import com.iquan.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {

		User user = new User();

		user.setUsername(username);
		user.setPassword(password);
		System.out.println(username);
		System.out.println(password);
		User userExist = userService.login(user);
		if (userExist != null) {
			// 使用session保持登录状态
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("user", userExist);
			return "loginsuccess";
		} else {
			return LOGIN;
		}
	}
}
