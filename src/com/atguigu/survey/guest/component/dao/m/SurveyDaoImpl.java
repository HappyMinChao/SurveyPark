package com.atguigu.survey.guest.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.SurveyDao;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;

@Repository
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao{
	public List getUncompletedListByUser(User user){
		Session session = this.getSession();
		List list = session.createQuery("From Survey s where s.user = ? and s.completed = false").setEntity(0, user).list();
		return list;
	}
	
	//查询该Survey对象的，完成或未完成的记录数
	@Override
	public int getSingleValue(boolean completed,User user) {
		String sql = "select count(*) from Survey s where s.completed=? and s.user=?";
		long totalRecord = (long) this.getSession().createQuery(sql)
				.setBoolean(0, completed).setEntity(1, user)
				.uniqueResult();
		return (int)totalRecord;
	}
	
	//获取所有完成的记录集合
	public List getCompletedList(boolean completed,User user){
		String sql = "from Survey s where s.completed=? and s.user=?";
		List list = this.getSession()
						.createQuery(sql)
						.setBoolean(0, completed)
						.setEntity(1, user)
						.list();
		return list;
	}
	
	//获取分页完成/未完成 的 记录集合
	public List getCompletedListPage(boolean completed,Integer pageNo , Integer pageSize,User user){
		int index = (pageNo-1)*pageSize;
		String sql = "From Survey s where s.completed=? and s.user = ?";
		List list = this.getSession()
						.createQuery(sql)
						.setBoolean(0, completed)
						.setEntity(1, user)
						.setFirstResult(index)
						.setMaxResults(pageSize)
						.list();
		return list ;
	}

	@Override
	public List<Survey> getTopCompletedSurvey() {
		String hql = "From Survey s where s.completed=true order by s.completedTime desc";
		List<Survey> list = this.getSession().createQuery(hql).setMaxResults(10).list();
		return list;
	}

	@Override
	public Page getPageCompletedSurvey(String pageStrNo, int pageSize) {
		//1.查询总记录数
		String sql = "select count(*) From Survey survey where survey.completed=completed";
		long totalRecordLong = (long) this.getSession().createQuery(sql).uniqueResult();
		int totalRecord = (int)totalRecordLong;
		Page page = new Page<Survey>(pageStrNo, pageSize+"", totalRecord);
		//计算index
		int index = (page.getCurrentPageNo()-1)*pageSize;
		
		sql = "From Survey s where s.completed=completed";
		
		List dataList = this.getSession()
						.createQuery(sql)
						.setFirstResult(index)
						.setMaxResults(pageSize)
						.list();
		page.setList(dataList);
		return page ;
	}

	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {
		String sql = "INSERT INTO engage(USER_ID, SURVEY_ID) VALUES(?,?)";
		this.getSession().createSQLQuery(sql).setInteger(0, userId).setInteger(1, surveyId).executeUpdate();
	}

	@Override
	public Page getMyEngageSurveyWithPage(Integer userId,String currentPageNo,String pageSize) {
		
		//1.获取总记录数
		String sql = "select count(*) from engage where user_id = ?";
		BigInteger totalRecord = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0,userId).uniqueResult();
		
		Page<Survey> page = new Page<Survey>(currentPageNo, pageSize, totalRecord.intValue());
		
		//查询出我参与调查的surveyId数组
		sql = "SELECT SURVEY_ID FROM engage WHERE USER_ID = ?";	
		int index = ( page.getCurrentPageNo()-1 ) * page.getPageSize();
		List<Integer> list = this.getSession().createSQLQuery(sql)
												.setInteger(0, userId)
												.setFirstResult(index)
												.setMaxResults(page.getPageSize())
												.list();
		
		List<Survey> surveyList = new ArrayList<Survey>();
		
		for(int i = 0 ; i < list.size(); i++){
			sql = "From Survey s left join fetch s.bagSet where s.surveyId = ?";
			Survey survey = (Survey) this.getSession().createQuery(sql).setInteger(0, list.get(i)).uniqueResult();
			surveyList.add(survey);
			
		}
		
		page.setList(surveyList);
		
		return page;
	}

}
