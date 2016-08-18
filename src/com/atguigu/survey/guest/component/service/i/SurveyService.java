package com.atguigu.survey.guest.component.service.i;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;

public interface SurveyService extends BaseService<Survey>{
	
	public abstract List getUncompletedListByUser(User user);
	public abstract Page<Survey> getUncompletedPage(String pageNo, String pageSize,User user);
	public abstract Page<Survey> getCompletedPage(String pageNo, String pageSize,User user);
	public abstract boolean completedSurvey(Integer surveyId);
	public abstract void deleteSurvey(Survey survey);
	public abstract List<Survey> getTopCompletedSurvey();
	public abstract List<Survey> getTopHotSurvey();
	public abstract Page findAllAvailableSurvey(String string, int i);
	public abstract void saveEngageMsg(Integer userId, Integer surveyId);
	public abstract Page getMyEngageSurveyWithPage(Integer userId,String currentPageNo,String pageSize);
	public abstract HSSFWorkbook generateWorkBook(Integer surveyId);
}
