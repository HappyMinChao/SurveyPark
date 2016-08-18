package com.atguigu.survey.guest.entity;

import java.util.Set;

public class Bag {
	
	private Integer bagId;
	private String bagName;
	private Survey survey;
	private Set questionSet;
	
	private int bagOrder;
	
	public int getBagOrder() {
		return bagOrder;
	}

	public void setBagOrder(int bagOrder) {
		this.bagOrder = bagOrder;
	}

	public Integer getBagId() {
		return bagId;
	}
	
	public Bag(Integer bagId, String bagName, Survey survey) {
		super();
		this.bagId = bagId;
		this.bagName = bagName;
		this.survey = survey;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	public String getBagName() {
		return bagName;
	}
	public void setBagName(String bagName) {
		this.bagName = bagName;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	public Set getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(Set questionSet) {
		this.questionSet = questionSet;
	}
	
	public Bag() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
