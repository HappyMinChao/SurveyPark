package com.atguigu.survey.guest.entity;

import java.util.Date;
import java.util.Set;

public class Survey {
	
	private Integer surveyId;
	private String title;
	private String logoPath = "/resources_static/logo.gif";
	private User user;
	private boolean completed;
	private Date completedTime;
	
	private Integer minBagOrder;
	private Integer maxBagOrder;
	
	

	private Set bagSet;
	
	public Survey() {
		
	}
	
	public Set getBagSet() {
		return bagSet;
	}

	public void setBagSet(Set bagSet) {
		this.bagSet = bagSet;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public boolean isUncompleted() {
		return completed;
	}

	public void setUncompleted(boolean completed) {
		this.completed = completed;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Integer getMinBagOrder() {
		return minBagOrder;
	}

	public void setMinBagOrder(Integer minBagOrder) {
		this.minBagOrder = minBagOrder;
	}

	public Integer getMaxBagOrder() {
		return maxBagOrder;
	}

	public void setMaxBagOrder(Integer maxBagOrder) {
		this.maxBagOrder = maxBagOrder;
	}

	
	
	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", title=" + title
				+ ", logoPath=" + logoPath  + ", completed="
				+ completed + "]";
	}
	
}
