package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

@Transactional
public interface LogService extends BaseService<Log> {

	void createTableByName(String tableName);

	List<String> getAllLogsTable();

	void saveLog(Log log);

	Page<Log> getLogFromTablesPage(String currentPageNoStr, String pageSizeStr);

}
