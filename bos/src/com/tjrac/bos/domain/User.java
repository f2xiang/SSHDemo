package com.tjrac.bos.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{
	private Integer id;
	
	private String username;
	
	private String password;
	
	private Double salary;
	
	private Date birthday;
	
	private String gender;
	
	private String station;
	
	private String telephone;
	
	private String remark;
	
	private Set<Noticebill> noticebills = new HashSet<Noticebill>();
	
	private Set<Role> roles = new HashSet<Role>();
	

	public String getRoleNames(){
		String names = "";
		for (Role role : roles) {
			names += role.getName() + " ";
		}
		return names;
	}
	
	
	public String getFormatbirthday(){
		if(birthday != null){
			return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}else{
			return "δ�ύ����";
		}
	}
	
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Noticebill> getNoticebills() {
		return noticebills;
	}

	public void setNoticebills(Set<Noticebill> noticebills) {
		this.noticebills = noticebills;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	
}
