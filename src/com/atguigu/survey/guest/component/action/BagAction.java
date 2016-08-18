package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.QuestionSetNotNullException;
import com.atguigu.survey.guest.entity.Survey;

@Controller
@Scope("prototype")
public class BagAction extends BaseAction<Bag> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BagService bagService;

	private Integer bagId;
	//================Action方法区=====================
		
	public String toMove(){
		return "toMovePage";
	}
	
	public String update(){
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);
		bagService.updateEntity(t);
		return "toSurveyDesign";
	}
	

	public void prepareEdit(){
		this.t = bagService.getEntityById(bagId);
	}
	
	public String edit(){
		
		return "toEditBag";
	}
	
	public String remove(){
		
		try {
			bagService.removeBag(t);
		} catch (Exception e) {
			throw  new QuestionSetNotNullException();
		}
		return "toGlobalMess";
	}
	
	public String saveBag(){
		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);
		System.out.println(t);
		bagService.saveEntity(t);
		return "toSurveyDesign";
	}
	
	//===============get/set方法区=====================
	
	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}
	
}
