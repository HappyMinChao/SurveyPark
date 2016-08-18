package com.atguigu.survey.guest.model;

import java.util.List;

public class QuestionStatisticsModel {
	
	//图标的标题
	private String questionName;
	//参与的总人数
	private int totalCount;
	//选项标题  选项被选次数
	private List<OptionStatisticsModel> osmList;

	public QuestionStatisticsModel() {
	}

	public QuestionStatisticsModel(String questionName, int totalCount,
			List<OptionStatisticsModel> osmList) {
		super();
		this.questionName = questionName;
		this.totalCount = totalCount;
		this.osmList = osmList;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OptionStatisticsModel> getOsmList() {
		return osmList;
	}

	public void setOsmList(List<OptionStatisticsModel> osmList) {
		this.osmList = osmList;
	}

	@Override
	public String toString() {
		return "QuestionStatisticsModel [questionName=" + questionName
				+ ", totalCount=" + totalCount + ", osmList=" + osmList + "]";
	}

}
