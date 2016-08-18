package com.atguigu.survey.base.m;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseAction<T> 
		extends ActionSupport 
		implements RequestAware,
				   SessionAware,
				   ApplicationAware,
				   ParameterAware,
				   ServletRequestAware,
				   ServletContextAware,
				   ModelDriven<T>,
				   Preparable{

	private static final long serialVersionUID = 1L;
	private Class<T> entityType;
	protected T t;
	protected ServletContext servletContext;
	protected HttpServletRequest request;
	protected Map<String, String[]> parametersMap;
	protected Map<String, Object> appMap;
	protected Map<String, Object> sessionMap;
	protected Map<String, Object> reqMap;
	
	//编辑调查、创建调查时，上传图片大小限制出错时，跳转不同页面，使用input来标识
	protected String input ;	
	 //由于调查、包裹、问题Action中都用到了surveyId属性，所以提取出来
	protected Integer surveyId;  
	
	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	

	public BaseAction() {
		this.entityType = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		try {
			//要赋值给t成员变量再返回，否则其他地方就拿不到这个对象的引用了
			t = entityType.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void prepare() throws Exception {}

	//返回t的实例对象：通过反射创建T类型的空对象
	public T getModel() {
		
		return t;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parametersMap = arg0;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.appMap = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.reqMap = arg0;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
}
