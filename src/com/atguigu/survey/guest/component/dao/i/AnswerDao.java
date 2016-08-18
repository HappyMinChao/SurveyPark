package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Answer;

public interface AnswerDao extends BaseDao<Answer> {

	public abstract void batchSaveAnswerList(List<Answer> answerList);

	public abstract int getEngageCount(Integer questionId);

	public abstract int getOptionCount(Integer questionId, String value);

	public abstract List getTextOtherList(Integer questionId);

	public abstract List getMainTextList(Integer questionId);

	public abstract List<Answer> getAnswerBySurveyId(Integer surveyId);

	public abstract List<String> getUUIDBySurveyId(Integer surveyId);

	public abstract List<Answer> getAnswerByUUIDAndSurveyId(Integer surveyId,
			String uuid);

	public abstract int getAnswerCount(Integer surveyId);

}
