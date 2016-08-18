package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;

public interface SurveyDao extends BaseDao<Survey>{
	public abstract List getUncompletedListByUser(User user);
	//查询该Survey对象完成/未完成的不带分页的
	public List getCompletedList(boolean completed,User user);
	//查询该Survey对象的，完成或未完成的记录数
	public abstract int getSingleValue(boolean completed,User user); 
	//查询该Survey对象完成/未完成的带分页的
	public List getCompletedListPage(boolean completed,Integer pageNo , Integer pageSize,User user);
	public abstract List<Survey> getTopCompletedSurvey();
	public abstract Page getPageCompletedSurvey(String pageStrNo, int pageSize);
	public abstract void saveEngageMsg(Integer userId, Integer surveyId);
	public abstract Page getMyEngageSurveyWithPage(Integer userId,String currentPageNo,String pageSize);
}
