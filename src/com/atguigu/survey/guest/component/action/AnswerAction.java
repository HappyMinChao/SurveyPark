package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.AnswerService;

@Controller
@Scope("prototype")
public class AnswerAction extends BaseAction<AnswerAction> {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	AnswerService answerService;
	private Integer surveyId;
	private Integer questionId;
	
	


	//===============Action方法区===================
	public String saveAnswer(){
		
		return null;
	}
	
	//================get/set方法区==================
	

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
	
}
