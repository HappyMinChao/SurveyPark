package com.atguigu.survey.guest.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.AnswerDao;
import com.atguigu.survey.guest.entity.Answer;

@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

	@Override
	public void batchSaveAnswerList(List<Answer> answerList) {
		
		String sql = "INSERT INTO answers(`QUESTION_ID`,`SURVEY_ID`,`ANSWER_TIME`,`UUID`,`MAIN_ANSWER`,`OTHER_ANSWER`) VALUES(?,?,?,?,?,?)";
		
		List<Object[]> params = new ArrayList<>();
		for (int i = 0; i < answerList.size(); i++) {
			
			Answer answer = answerList.get(i);
			Object[] param = new Object[6];
			param[0] = answer.getQuestionId();
			param[1] = answer.getSurveyId();
			param[2] = answer.getAnswerTime();
			param[3] = answer.getUuid();
			param[4] = answer.getMainAnswer();
			param[5] = answer.getOtherAnswer();
			
			params.add(param);
		}
		
		this.doBatchWork(sql, params);
		
	}

	@Override
	public int getEngageCount(Integer questionId) {
		String sql = "SELECT COUNT(DISTINCT UUID) FROM ANSWERS WHERE QUESTION_ID = ?";
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, questionId).uniqueResult();
		return count.intValue();
	}

	@Override
	public int getOptionCount(Integer questionId, String value) {
	
		//以question_id 等于2 为例： 选出question_id = 2 并且 main_answer 前后拼接上',' 后  包含 ',1,'的次数
		String sql = "SELECT COUNT(DISTINCT UUID) FROM ANSWERS WHERE QUESTION_ID = ? "
							+ "AND CONCAT(',',MAIN_ANSWER,',') LIKE ?";
		
		BigInteger count = (BigInteger)this.getSession().createSQLQuery(sql)
										.setInteger(0, questionId)
										.setString(1, "%,"+value+",%")
										.uniqueResult();
		
		
		return count.intValue();
		
	}

	@Override
	public List getTextOtherList(Integer questionId) {
		String sql = "SELECT OTHER_ANSWER FROM ANSWERS "
							+ "WHERE QUESTION_ID = ? "
							+ "AND OTHER_ANSWER IS NOT NULL "
							+ "AND OTHER_ANSWER != ''";
		List list = this.getSession().createSQLQuery(sql).setInteger(0, questionId).list();
		return list;
	}

	@Override
	public List getMainTextList(Integer questionId) {
		String sql = "SELECT MAIN_ANSWER FROM answers "
								+ "	WHERE QUESTION_ID = ? "
								+ "	AND MAIN_ANSWER IS NOT NULL "
								+ "AND MAIN_ANSWER != ''";
		return this.getSession()
					.createSQLQuery(sql)
					.setInteger(0, questionId)
					.list();
	}

	@Override
	public List<Answer> getAnswerBySurveyId(Integer surveyId) {

		String hql = "From Answer a where a.surveyId=?";
		
		List<Answer> list = this.getSession().createQuery(hql).setInteger(0, surveyId).list();
		
		return list;
	}

	@Override
	public List<String> getUUIDBySurveyId(Integer surveyId) {
		String sql = "SELECT DISTINCT UUID FROM answers WHERE SURVEY_ID = ?";
		List<String> list = this.getSession().createSQLQuery(sql).setInteger(0, surveyId).list();
		return list;
	}

	@Override
	public List<Answer> getAnswerByUUIDAndSurveyId(Integer surveyId, String uuid) {
		String sql = "FROM Answer a WHERE a.surveyId = ? and a.uuid=?";
		List<Answer> list = this.getSession().createQuery(sql).setInteger(0, surveyId).setString(1, uuid).list();
		return list;
	}

	@Override
	public int getAnswerCount(Integer surveyId) {
		String sql = "SELECT count(DISTINCT UUID) FROM answers WHERE SURVEY_ID = ?";
		BigInteger result = (BigInteger)this.getSession().createSQLQuery(sql).setInteger(0, surveyId).uniqueResult();
		return result.intValue();
	}
}
