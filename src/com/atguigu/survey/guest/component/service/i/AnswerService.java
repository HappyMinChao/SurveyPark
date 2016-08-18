package com.atguigu.survey.guest.component.service.i;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Answer;

@Transactional
public interface AnswerService extends BaseService<Answer> {

	public abstract void saveAnswer(Map<Integer, Map<String, String[]>> allBagAnwerMap,
			Integer surveyId);
	
	

}
