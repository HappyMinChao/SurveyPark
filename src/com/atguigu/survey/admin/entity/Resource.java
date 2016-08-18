package com.atguigu.survey.admin.entity;

public class Resource {
	//1.资源的id
	private Integer resourceId;
	//2.资源的name
	private String resourceName;
	//3.资源关联的actionName
	private String actionName;
	//4.权限位
	private Integer resPos;
	//5.权限码
	private long resCode;
	
	public Resource() {
		
	}

	public Resource(String resourceName, String actionName, Integer resPos,
			long resCode) {
		super();
		this.resourceName = resourceName;
		this.actionName = actionName;
		this.resPos = resPos;
		this.resCode = resCode;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getResPos() {
		return resPos;
	}

	public void setResPos(Integer resPos) {
		this.resPos = resPos;
	}

	public long getResCode() {
		return resCode;
	}

	public void setResCode(long resCode) {
		this.resCode = resCode;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceName="
				+ resourceName + ", actionName=" + actionName + ", resPos="
				+ resPos + ", resCode=" + resCode + "]";
	}

}
