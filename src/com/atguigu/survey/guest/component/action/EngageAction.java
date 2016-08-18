package com.atguigu.survey.guest.component.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.AnswerService;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class EngageAction extends BaseAction<Survey> implements UserAware{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private BagService bagService;
	@Autowired
	private AnswerService answerService;

	private Integer surveyId;
	private Integer bagId;
	private User user;
	
	private Bag currentBag;
	
	//======================Action方法区====================
	
	public String engage(){
		/*
		 * 每次点击上一个包裹、下一个包裹……都会提交参数， 从session域中获取allBagAnswerMap备用
		 */
		Map<Integer, Map<String, String[]>> allBagAnwerMap = 
					(Map<Integer, Map<String, String[]>>)this.sessionMap.get(GlobalNames.ALL_BAG_ANSWER_MAP);
		
		
		String subButName = DataProcessUtils.getSubBtnName(parametersMap);
		//submit_prev submit_next submit_done submit_quit
		
		//上一个包裹
		if("submit_prev".equals(subButName)){
			allBagAnwerMap.put(bagId, parametersMap);
			//获取改bagId的上一个包裹
			currentBag = bagService.getPrevBag(surveyId, bagId);
			//reqMap.put(GlobalNames.CURRENT_BAG, currentBag);
		}
		
		//下一个包裹
		if("submit_next".equals(subButName)){
			allBagAnwerMap.put(bagId, parametersMap);
			//获取改bagId的下一个包裹
			currentBag = bagService.getNextBag(surveyId, bagId);
			//reqMap.put(GlobalNames.CURRENT_BAG, currentBag);
		}
		
		//保存答案
		if("submit_done".equals(subButName)){
			//提交保存答案也要把最后一页的数据放在allBagAnwerMap中
			allBagAnwerMap.put(bagId, parametersMap);
			
			//完成包裹,保存数据
			answerService.saveAnswer(allBagAnwerMap,surveyId);
			
			try {
				surveyService.saveEngageMsg(user.getUserId(), surveyId);
			} catch (Exception e) {
			}
			
			return "toIndexAction";
		}
		
		//放弃调查
		if("submit_quit".equals(subButName)){
			//放弃包裹,清空session,回到首页
			sessionMap.remove(GlobalNames.CURRENT_SURVEY);
			sessionMap.remove(GlobalNames.CURRENT_BAG);
			return "toIndexAction";
		}
		return "toEngageSurvey";
	}
	
	
	public String entry(){
		/**
		 * 把当前的Survey放在session域中，一遍在网页中使用
		 */
		Survey currentSurvey = surveyService.getEntityById(surveyId);
		sessionMap.put(GlobalNames.CURRENT_SURVEY, currentSurvey);
		
		/*
		 * 在我们点击进入调查时，把ALL_BAG_ANSWER_MAP，
		 * 		该调查中所有包裹提交的答案放在session中
		 * 	key: bagId
		 * 	value: paramtersMap:  其中包含了提交包裹答案的所有参数
		 */
		Map<Integer, Map<String, String[]>> allBagAnswerMap = 
							new HashMap<Integer, Map<String, String[]>>();
		
		//把allBagAnswerMap放在session域中
		this.sessionMap.put(GlobalNames.ALL_BAG_ANSWER_MAP, allBagAnswerMap);
		currentBag = bagService.getFirstBag(surveyId);

		reqMap.put(GlobalNames.CURRENT_BAG, currentBag);
		
		return "toEngageSurvey";
	}
	
	//===================get/set方法区====================
	
	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	
	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public Bag getCurrentBag() {
		return currentBag;
	}

	public void setCurrentBag(Bag currentBag) {
		this.currentBag = currentBag;
	}


	@Override
	public void setUser(User user) {
		this.user = user;
	}

	
}
