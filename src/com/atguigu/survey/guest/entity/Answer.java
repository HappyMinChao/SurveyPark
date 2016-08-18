package com.atguigu.survey.guest.entity;

import java.util.Date;

/**
 * 
 * 	在此不会用来显示问题的答案，所以没有必要保存bagId，
 * 		在以后的功能中只需要根据surveyId 和 questionId进行答案统计
 * 	还需要保存答案的变量 mainAnswer、
 * 
 * @author du_minchao
 *
 */

public class Answer {
	
	private Integer answerId;
	private Integer surveyId;
	private Integer questionId;
	
	private Date answerTime;
	private String uuid;
	
	//保存答案数据的主要属性， 包括"和主题性一致"的其他项数据
	private String mainAnswer;
	//保存文本框形式的其他项
	private String otherAnswer;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(Integer answerId, Integer surveyId, Integer questionId,
			Date answerTime, String uuid, String mainAnswer, String otherAnswer) {
		super();
		this.answerId = answerId;
		this.surveyId = surveyId;
		this.questionId = questionId;
		this.answerTime = answerTime;
		this.uuid = uuid;
		this.mainAnswer = mainAnswer;
		this.otherAnswer = otherAnswer;
	}
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMainAnswer() {
		return mainAnswer;
	}
	public void setMainAnswer(String mainAnswer) {
		this.mainAnswer = mainAnswer;
	}
	public String getOtherAnswer() {
		return otherAnswer;
	}
	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", surveyId=" + surveyId
				+ ", questionId=" + questionId + ", answerTime=" + answerTime
				+ ", uuid=" + uuid + ", mainAnswer=" + mainAnswer
				+ ", otherAnswer=" + otherAnswer + "]";
	}
	

	

}
