package com.atguigu.survey.admin.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.utils.DataProcessUtils;

//继承使用工作bean类
public class LogScheduler extends QuartzJobBean{
	
	//在application.xml中配置自动注入
	private LogService logService;
	
	//每个月的15号自动创建三个月的日志表，需要用到LogService
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		//根据当前月的偏移量，自动创建三个月的日志表
		String tableName = DataProcessUtils.generateTableName(0);
		logService.createTableByName(tableName);
		tableName = DataProcessUtils.generateTableName(1);
		logService.createTableByName(tableName);
		tableName = DataProcessUtils.generateTableName(2);
		logService.createTableByName(tableName);
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
