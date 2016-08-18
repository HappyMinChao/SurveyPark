package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.i.BaseDao;

public interface LogDao extends BaseDao<Log> {

	void createTableByName(String tableName);

	List<String> getAllLogsTable();

	void saveLog(Log log);

	List<Log> getLimitedLogList(int pageNo, int pageSize);

	int getTotalCount();

}
