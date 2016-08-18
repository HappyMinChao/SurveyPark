package com.atguigu.survey.advisor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Log;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.GlobalNames;
import com.opensymphony.xwork2.ActionContext;

//没有采用注解的方式， 采用的bean的方式，因为一个类来要设置扫描路径
//记得导包
public class LogAspect {

	//保存log需要logService
	LogService logService;
	
	Map map = new HashMap();
	/*
	 * 在每次执行方法是， 创建一个Log对象保存到数据库中
	 */
	public Object record(ProceedingJoinPoint joinPoint){
		
		Object result = null;
		
		String operate = getOperate();
		String methodReturn = null;
		String methodType = joinPoint.getSignature().getDeclaringType().toString();
		String methodName = joinPoint.getSignature().getName();
		String methodArgs = Arrays.asList(joinPoint.getArgs()).toString();
		String methodResultMesg = null;
		String operateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		
		//调用目标方法  proceed: 开始，继续进行  result方法返回值
		try {
			result = joinPoint.proceed();
			methodReturn = result == null?"无":result.toString();
		} catch (Throwable e) {
			methodResultMesg = "失败"+e.getMessage();
		}finally{
			Log log = new Log(operate, methodReturn, methodType, methodName, methodArgs, methodResultMesg, operateTime);
			logService.saveLog(log);
		}
		
		return result;
	}
	

	private String getOperate(){

		//获取到session
		Admin admin = (Admin)ActionContext.getContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User)ActionContext.getContext().getSession().get(GlobalNames.LOGIN_USER);
		String adminStr = (admin == null)?"[]" : admin.toString();
		String userStr = (user == null) ? "[]" : user.toString();
		String operate = adminStr + userStr;
		return operate;
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
}
