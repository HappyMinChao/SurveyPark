package com.atguigu.survey.guest.entity;

import com.atguigu.survey.utils.DataProcessUtils;

public class Question {
	//主键
	private Integer questionId;
	
	//属于哪个包裹
	private Bag bag;
	
	//问题题干
	private String questionName;
	
	//题型
	private int questionType;
	
	//是否换行
	private boolean br;
	
	//是否设置其他项
	private boolean hasOther;
	
	//其他项形式
	//0:和主题性一致
	//1:文本框
	private int otherType;
	
	//=========需要特殊处理的属性===========
	//单选、多选、下拉列表的选项
	private String options;
	
	//矩阵式问题的行行标题组
	private String matrixRowTitles;
	
	//矩阵式问题的列行标题组
	private String matrixColTitles;
	//矩阵式问题 
	private String matrixOptions;
	
	//========================================
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public boolean isBr() {
		return br;
	}

	public void setBr(boolean br) {
		this.br = br;
	}

	public boolean isHasOther() {
		return hasOther;
	}

	public void setHasOther(boolean hasOther) {
		this.hasOther = hasOther;
	}

	public int getOtherType() {
		return otherType;
	}

	public void setOtherType(int otherType) {
		this.otherType = otherType;
	}
	
	public void setBag(Bag bag) {
		this.bag = bag;
	}
	
	public Bag getBag() {
		return bag;
	}
	//===========需要特殊处理的属性==================
	
	//1.常规的getXXX()方法
	public String getOptions() {
		return options;
	}
	//2.用于保存数据的setXxx()方法
	public void setOptions(String options) {
		this.options = DataProcessUtils.processStrForSave(options);
	}
	
	//3.用于回显数据的getXxxForShow()方法
	public String getOptionsForShow() {
		return DataProcessUtils.processStrForShow(options);
	}
	
	//4.用于展示的getXxxArray()方法
	public String[] getOptionsArray() {
		return DataProcessUtils.convertStrToArr(options);
	}
	
	//---------------------matrixRowTitles------------------------
	//1.常规的getXxx()方法
	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	//2.用于保存数据的setXxx()方法
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = DataProcessUtils.processStrForSave(matrixRowTitles);
	}

	//3.用于回显数据的getXxxForShow()方法
	public String getMatrixRowTitlesForShow() {
		return DataProcessUtils.processStrForShow(matrixRowTitles);
	}
	
	//4.用于展示的getXxxArray()方法
	public String[] getMatrixRowTitlesArray() {
		return DataProcessUtils.convertStrToArr(matrixRowTitles);
	}
	
	//---------------------matrixColTitles------------------------
	//1.常规的getXxx()方法
	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	//2.用于保存数据的setXxx()方法
	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = DataProcessUtils.processStrForSave(matrixColTitles);
	}
	
	//3.用于回显数据的getXxxForShow()方法
	public String getMatrixColTitlesForShow() {
		return DataProcessUtils.processStrForShow(matrixColTitles);
	}
	
	//4.用于展示的getXxxArray()方法
	public String[] getMatrixColTitlesArray() {
		return DataProcessUtils.convertStrToArr(matrixColTitles);
	}

	//---------------------matrixOptions------------------------
	//1.常规的getXxx()方法
	public String getMatrixOptions() {
		return matrixOptions;
	}

	//2.用于保存数据的setXxx()方法，数据库保存格式: 选项01,选项02,选项03,选项04  
	public void setMatrixOptions(String matrixOptions) {
		this.matrixOptions = DataProcessUtils.processStrForSave(matrixOptions);
	}
	
	//3.用于回显数据的getXxxForShow()方法, 用于页面显示格式: 选项01\r\n选项02\r\n选项03\r\n选项04  
	public String getMatrixOptionsForShow() {
		return DataProcessUtils.processStrForShow(matrixOptions);
	}
	
	//4.用于展示的getXxxArray()方法  把  选项01,选项02,选项03,选项04 转换成数组
	public String[] getMatrixOptionsArray() {
		return DataProcessUtils.convertStrToArr(matrixOptions);
	}
}
