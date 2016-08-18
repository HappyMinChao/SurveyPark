package com.atguigu.survey.guest.component.service.i;

import com.atguigu.survey.guest.entity.Question;

public interface QuestionService {

	public abstract void saveOrUpdate(Question t);

	public abstract Question getEntityById(Integer questionId);

	public abstract void remove(Integer questionId);

}
