package com.atguigu.survey.guest.component.dao.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements
		QuestionDao {

	@Override
	public void saveOrUpdate(Question question) {
		this.getSession().saveOrUpdate(question);
	}

	@Override
	public List<Question> getQuestionBySurvey(Survey survey) {
		
		List<Question> questionList = new ArrayList<Question>();
		
		Set<Bag> bagSet = survey.getBagSet();
		
		for (Bag bag : bagSet) {
			Set<Question> questionSet = bag.getQuestionSet();
			for (Question question : questionSet) {
				questionList.add(question);
			}
		}
		
		return questionList;
	}

	
}
