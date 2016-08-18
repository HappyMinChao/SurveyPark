package com.atguigu.survey.admin.component.listenner;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.utils.DataProcessUtils;

public class LogInitListener implements ApplicationListener {
	
	private LogService logService;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		//如果是这个事件，则表示web应用启动了。
		if(event instanceof ContextRefreshedEvent){
			
			String tableName = DataProcessUtils.generateTableName(0);
			logService.createTableByName(tableName);
			
			tableName = DataProcessUtils.generateTableName(1);
			logService.createTableByName(tableName);
			
			tableName = DataProcessUtils.generateTableName(2);
			logService.createTableByName(tableName);
		}
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
