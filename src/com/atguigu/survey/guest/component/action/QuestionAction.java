package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.entity.Question;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	QuestionService questionService ;
	
	//BaseAction中有 survey 属性
	private Integer bagId;
	private Integer questionId;
	
	//================Action方法区=================
	
	public String remove(){
		questionService.remove(questionId);
		//在此传递了一个surveyId，为了能跳转到相应的survey页面
		return "toGlobalMess";
	}
	
	/*
	 * 由于在编辑时已经去往design页面，携带了questionId,并在隐含表单域中
	 * public void prepareSaveOrUpdate(){
		//如果注入的questionId为大于0的数说明为修改
		if(questionId != null){
			//this.t = questionService.getEntityById(questionId);
		}
	}*/
	
	public String saveOrUpdate(){
		questionService.saveOrUpdate(t);
		return "toSurveyDesign";
	}
	
	public void prepareToQuestionDesign(){
		//如果注入的questionId为大于0的数说明为修改
		if(questionId != null){
			this.t = questionService.getEntityById(questionId);
		}
	}
	
	public String toQuestionDesign(){
		return "toQuestionDesign";
	}
	
	public String toChoosenType(){
		return "toChoosenType";
	}
	
	//===================get/set方法区=========================

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}


	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
}
