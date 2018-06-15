package com.iquan.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
private Integer id;
private String username;
private String password;
public Set<Visit> getSetUserVisit() {
	return setUserVisit;
}
public void setSetUserVisit(Set<Visit> setUserVisit) {
	this.setUserVisit = setUserVisit;
}
private Set<Visit> setUserVisit=new HashSet<Visit>();
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
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
}
