package com.atguigu.survey.admin.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.LogDao;
import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.model.Page;

@Service
public class LogServiceImpl extends BaseServcieImpl<Log> implements LogService {

	@Autowired
	private LogDao logDao;
	
	@Override
	public void createTableByName(String tableName) {

		logDao.createTableByName(tableName);
		
	}
	
	@Override
	public List<String> getAllLogsTable(){
		List<String> list = logDao.getAllLogsTable();
		return list;
	}
	
	@Override
	public void saveLog(Log log){
		logDao.saveLog(log);
	}
	
	@Override
	public Page<Log> getLogFromTablesPage(String currentPageNoStr, String pageSizeStr){
		
		int totalRecord = logDao.getTotalCount();
		
		Page page = new Page<>(currentPageNoStr, pageSizeStr, totalRecord);
		
		List<Log> dataList = logDao.getLimitedLogList(page.getCurrentPageNo(), page.getPageSize());
		page.setList(dataList);
		
		return page;
	}
}
