package com.atguigu.survey.admin.component.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class ExcelAction extends BaseAction<Survey> {
	
	private String currentPageNo ;

	@Autowired
	private SurveyService surveyService;
	
	private InputStream inputStream;
	private int contentLength;
	private String fileName;
	
	//======================Action方法区===========================
	
	
	public String exportExcel() throws IOException{
		
		//1.生成HSSFWorkBook对象  
		HSSFWorkbook workBook = surveyService.generateWorkBook(t.getSurveyId());
		
		//2.将输出流转换为输入流
		//①将工作表数据写入到字节数组输出流中
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
		workBook.write(baoStream);
		
		//②从字节数组输出流中获取字节数组
		byte[] byteArray = baoStream.toByteArray();
		
		//③根据字节数组创建字节数组输入流对象，赋值给inputStream
		inputStream = new ByteArrayInputStream(byteArray);
		
		//3.给其他相关属性赋值
		contentLength = inputStream.available();
		
		return "exportExcel";
	}
	
	public String showAllSurvey(){
		
		if(sessionMap.get(GlobalNames.LOGIN_ADMIN) == null){
			return "toAdminLoginPage";
		}
		
		//登录成功，分页获取到所有完成可参与的调查
		Page<Survey> page = surveyService.findAllAvailableSurvey(currentPageNo, 4);
		reqMap.put(GlobalNames.PAGE, page);	
		return "toExcelListPage";
	}
	
	//============================get/set方法区==============================
	
	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public int getContentLength() {
		return contentLength;
	}

	public String getFileName() {
		return System.nanoTime()+".xls";
	}

}
