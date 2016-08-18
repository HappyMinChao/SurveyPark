package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.LogDao;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.utils.DataProcessUtils;

@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	@Override
	public void createTableByName(String tableName) {
		String sql = "CREATE TABLE IF NOT EXISTS survey1022."+tableName+" like survey1022.logs";
		System.out.println(sql);
		this.getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
	@Override
	public List<String> getAllLogsTable(){
		String sql = "SELECT TABLE_NAME "
					+ "FROM `information_schema`.`TABLES` "
					+ "WHERE TABLE_NAME LIKE 'logs_%'";
		
		List<String> list = this.getSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public void saveLog(Log log) {
		
		//获取到当前表明
		String tableName = DataProcessUtils.generateTableName(0);
		
		String sql = "INSERT INTO "+tableName+"("
									+ "OPERATE,"
									+ "METHOD_RETURN,"
									+ "METHOD_TYPE,"
									+ "METHOD_NAME,"
									+ "METHOD_ARGS,"
									+ "METHOD_RESULT_MESG,"
									+ "OPERATE_TIME) "
									+ "VALUES(?,?,?,?,?,?,?)";
		
		this.getSession().createSQLQuery(sql)
							.setString(0, log.getOperate())
							.setString(1, log.getMethodReturn())
							.setString(2, log.getMethodType())
							.setString(3, log.getMethodName())
							.setString(4, log.getMethodArgs())
							.setString(5, log.getMethodResultMesg())
							.setString(6, log.getOperateTime())
							.executeUpdate();
		
	}

	
	@Override
	public int getTotalCount() {
		
		List<String> tableNameList = getAllLogsTable();
		String subSelect = DataProcessUtils.generateSubSelect(tableNameList);
		
		//SELECT COUNT(log_union.log_id) FROM (SELECT * FROM `logs` UNION SELECT * FROM logs_2016_3) log_union
		
		String sql = "select count(log_union.log_id) from ("+subSelect+") log_union";
		
		BigInteger count = (BigInteger)this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Log> getLimitedLogList(int pageNo, int pageSize) {
		
		List<Log> logList = new ArrayList<>();
		
		List<String> tableNameList = getAllLogsTable();
		String subSelect = DataProcessUtils.generateSubSelect(tableNameList);
		
		String sql = "select log_union.`LOG_ID`,"
							+ "log_union.`OPERATE`,"
							+ "log_union.`OPERATE_TIME`,"
							+ "log_union.`METHOD_TYPE`,"
							+ "log_union.`METHOD_NAME`,"
							+ "log_union.`METHOD_ARGS`,"
							+ "log_union.`METHOD_RETURN`,"
							+ "log_union.`METHOD_RESULT_MESG`"
							+ " from ("+subSelect+") log_union";
		
		int index = (pageNo - 1)*pageSize;
		
		List<Object[]> result = this.getSession().createSQLQuery(sql).setFirstResult(index).setMaxResults(pageSize).list();
		
		for (Object[] objects : result) {
			Integer logId = (Integer) objects[0];
			String operator = (String) objects[1];
			String operateTime = (String) objects[2];
			String methodType = (String) objects[3];
			String methodName = (String) objects[4];
			String methodArgs = (String) objects[5];
			String methodReturnValue = (String) objects[6];
			String methodResultMsg = (String) objects[7];
			
			Log log = new Log(logId,operator, methodReturnValue, methodType, methodName, methodArgs, methodResultMsg, operateTime);
			
			logList.add(log);
		}
		
		return logList;
	}
}
