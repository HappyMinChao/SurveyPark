package com.atguigu.survey.admin.component.intecept;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.GlobalNames;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invoke) throws Exception {

		//1.获取到Action
		ActionProxy proxy = invoke.getProxy();
		Object action = proxy.getAction();
		
		//2.获取user
		//①.获取到Session
		Map<String, Object> sessionMap = ServletActionContext.getContext().getSession();
		User user = (User)sessionMap.get(GlobalNames.LOGIN_USER);
		//②.如果user为空， 未登陆放行
		if(user == null) return invoke.invoke();
		
		if(action instanceof UserAware){
			UserAware actionAware = (UserAware)action;
			actionAware.setUser(user);
		}
			
		return invoke.invoke();
	}
	
	

}
