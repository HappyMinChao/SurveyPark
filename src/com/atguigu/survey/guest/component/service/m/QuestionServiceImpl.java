package com.atguigu.survey.guest.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.entity.Question;

@Service
public class QuestionServiceImpl extends BaseServcieImpl<Question> implements QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	@Override
	public void saveOrUpdate(Question question) {
		questionDao.saveOrUpdate(question);		
	}

	@Override
	public Question getEntityById(Integer questionId) {
		Question question = questionDao.getEntityById(questionId);
		return question;
	}

	@Override
	public void remove(Integer questionId) {
		Question question = questionDao.getEntityById(questionId);
		questionDao.deleteEntity(question);
	}

}
