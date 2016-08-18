package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;

@Transactional
public interface QuestionDao extends BaseDao<Question> {

	public abstract void saveOrUpdate(Question question);

	public abstract List<Question> getQuestionBySurvey(Survey survey);


}
