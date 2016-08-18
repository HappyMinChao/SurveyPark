package com.atguigu.survey.router;

public class SurveyToken {
	
	private SurveyToken(){
		//把SurvyeToken 与当前线程绑定
		ThreadLocal<SurveyToken> threadLocal = new ThreadLocal<>();
	}
	public static SurveyToken getSurveyToken(){
		SurveyToken surveyToken = new SurveyToken();
		return surveyToken;
	}
	
	//主要是传递SurveyId
	private Integer surveyId;
	
	//与当前线程绑定
	public void setThreadLocal(){
		//threadLocal.set(value);
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	
	

}
