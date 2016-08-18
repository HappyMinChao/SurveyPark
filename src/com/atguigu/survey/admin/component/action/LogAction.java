package com.atguigu.survey.admin.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 1L;
	
	private String currentPageNo;

	@Autowired
	private LogService logService;
	
	public String showLogs(){
		String pageSizeStr = "5";
		Page<Log> page = logService.getLogFromTablesPage(currentPageNo, pageSizeStr);
		reqMap.put(GlobalNames.PAGE, page);
		return "toShowLogs";
	}

	
	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
}
