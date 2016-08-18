package com.atguigu.survey.admin.entity;

import java.util.Set;


public class Authority {
	
	//1.权限的id
	private Integer authorityId;
	//2.权限的
	private String authorityName;
	//3.权限管理的资源
	private Set<Resource> resourceSet;
	
	public Authority() {
	}

	public Authority(Integer authorityId, String authorityName,
			Set<Resource> resourceSet) {
		super();
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.resourceSet = resourceSet;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Set<Resource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", authorityName="
				+ authorityName + "]";
	}
	
	
}
