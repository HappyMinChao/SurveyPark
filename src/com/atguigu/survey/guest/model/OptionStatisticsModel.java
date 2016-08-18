package com.atguigu.survey.guest.model;

public class OptionStatisticsModel {
	private String lable;
	private int count;
	
	public OptionStatisticsModel() {}

	public OptionStatisticsModel(String lable, int count) {
		super();
		this.lable = lable;
		this.count = count;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OptionStatisticsModel [lable=" + lable + ", count=" + count
				+ "]";
	}
	
}
