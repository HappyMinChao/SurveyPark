package com.atguigu.survey.guest.component.service.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;

public interface StatisticsService extends BaseService<Survey> {

	QuestionStatisticsModel getQsm(Integer questionId);

	List getTextOtherList(Integer questionId);

	List getMainTextList(Integer questionId);

	List<OptionStatisticsModel> getMatrixOsmList(Integer questionId,Integer rowNumber);

	List<OptionStatisticsModel> getOptionMatrixOsmList(Integer questionId,
			Integer rowNumber, Integer colNumber);

}
