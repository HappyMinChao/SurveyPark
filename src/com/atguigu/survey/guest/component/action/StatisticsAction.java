package com.atguigu.survey.guest.component.action;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.QuestionService;
import com.atguigu.survey.guest.component.service.i.StatisticsService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class StatisticsAction extends BaseAction<Survey> {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	QuestionService questionService;
	@Autowired 
	SurveyService surveyService;
	@Autowired
	StatisticsService statisticsService;
	
	private Integer questionId;
	
	private Integer width;
	private Integer height;

	private JFreeChart chart;
	private Integer rowNumber;
	
	private Integer colNumber;
	
	//=========================Action 方法区=============================
	
	public String showOptionMatrixChart(){
		
		List<OptionStatisticsModel> osmList = statisticsService.getOptionMatrixOsmList(questionId, rowNumber,colNumber);
		this.chart = DataProcessUtils.generateChartByOsmList(osmList);
		
		this.width = 250;
		this.height = 200;
		
		return "toChartResult";
	}
	
	public String showMatrixOptionPage(){
		Question question = questionService.getEntityById(questionId);
		reqMap.put(GlobalNames.CURRENT_QUESTION, question);
		return "toMatrixOptionPage";
	}
	
	public String showNormalMatrixChart(){
		
		/*//由于一个图片获取需要发送一次请求， 所以不可能发送一次请求获取一个图片集，这种想法是错无的。 
		List<QuestionStatisticsModel> qsmList = statisticsService.getMarixNormalQsm(questionId,rowNumber);
		this.chart = DataProcessUtils.generateChartByQsmList(sqmList);*/
		List<OptionStatisticsModel> osmList = statisticsService.getMatrixOsmList(questionId, rowNumber);
		this.chart = DataProcessUtils.generateChartByOsmList(osmList);
		
		this.width=400;
		this.height = 300;
		
		return "toChartResult";
	}
	
	
	public String showMatrixNomalPage(){
		
		Question question = questionService.getEntityById(questionId);
		reqMap.put(GlobalNames.CURRENT_QUESTION, question);
		
		return "toMatrixNomalPage";
	}
	
	public String showMainTextList(){
		List list = statisticsService.getMainTextList(questionId);
		reqMap.put(GlobalNames.LIST, list);
		
		return "toTextPage";
	}
	
	public String showOtherTextList(){
		
		List list = statisticsService.getTextOtherList(questionId);
		reqMap.put(GlobalNames.LIST, list);
		
		return "toTextPage";
	}

	public String showNormalChart() {
		
		//1.生成Chart对象赋值给this.chart
		QuestionStatisticsModel qsm = statisticsService.getQsm(questionId);
		
		this.chart = DataProcessUtils.generateChartByQsm(qsm);
		
		//2.设置宽高
		this.width = 1024;
		this.height = 768;
		
		return "toChartResult";
	}
	
	public void prepareShowSummary(){
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String showSummary(){
		
		/**
		 * QuestionStatisticsModel:
		 * 		questionName: 问题的标题
		 * 		totalCount: 回答此问题的总人数
		 * 		osmList:  
		 * 			OptionStatisticModel :	
		 * 				lable:选项标签
		 * 				count:该选项被选的次数
		 */
		
		//QuestionStatisticsModel qsm = statisticsService.getQsm(questionId);
		//将t放入request域中
		return "toSummaryPage";
	}
	
	//==========================get/set方法区=====================
	
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getColNumber() {
		return colNumber;
	}

	public void setColNumber(Integer colNumber) {
		this.colNumber = colNumber;
	}

}
