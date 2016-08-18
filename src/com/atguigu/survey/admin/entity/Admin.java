package com.atguigu.survey.admin.entity;

import java.util.Set;

public class Admin {
	
	private Integer adminId;
	private String adminName;
	private String adminPwd;
	
	private Set<Role> roleSet;
	
	private String resCode;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(Integer adminId, String adminName, String adminPwd) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
	}
	
	public Integer getAdminId() {
		return adminId;
	}
	
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminPwd() {
		return adminPwd;
	}
	
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName
				+ ", adminPwd=" + adminPwd + "]";
	}
	 
}
