package com.atguigu.survey.admin.component.intecept;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.guest.component.service.i.UserService;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;
import com.atguigu.survey.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInteceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invoke) throws Exception {

		Set<String> visitorAllowedAction = new HashSet<>();
		visitorAllowedAction.add("UserAction_register");
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("SurveyAction_toIndexAction");
		visitorAllowedAction.add("AdminAction_toAdminPage");
		visitorAllowedAction.add("AdminAction_login");

		ActionProxy proxy = invoke.getProxy();
		String actionName = proxy.getActionName();
		Object action = proxy.getAction();

		if (visitorAllowedAction.contains(actionName))
			return invoke.invoke();

		// 获取ioc容器
		WebApplicationContext ioc = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());
		ResourceService resourceService = ioc.getBean(ResourceService.class);
		RoleService roleService = ioc.getBean(RoleService.class);
		
		// 根据actionName获取到相关的resource对象
		Resource resource = resourceService.getResourceByActionName(actionName);
		long rescode = resource.getResCode();

		// 1.首先获取到admin或者user
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin admin = (Admin) session.getAttribute(GlobalNames.LOGIN_ADMIN);
		User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);

		
		
		//③如果user对象为null，则说明当前用户是游客
		if(user == null) {
			//目标ActionName是否在游客允许的集合中
			if(visitorAllowedAction.contains(actionName)) {
				//放行
				return invoke.invoke();
			}
		}else{
			//检查用户是否是付费用户
			if(user.isPayStatus()) {
				
				//检查当前会员是否到期
				long endTime = user.getEndTime();
				
				long time = new Date().getTime();
				
				if(endTime <= time) {
					//过期
					//重新计算权限码
					Role role = roleService.getRoleByName("普通登录用户");
					Set<Role> roleSet = user.getRoleSet();
					roleSet.add(role);
					int maxResPos = resourceService.getMaxResPos();
					String resCodeStr = DataProcessUtils.calculateResCode(roleSet, maxResPos);
					user.setResCode(resCodeStr);
					
					user.setPayStatus(false);
					ServletContext sc = ServletActionContext.getServletContext();
					WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
					UserService userService = context.getBean(UserService.class);
					userService.updateEntity(user);
					
					return "toVipPage";
				}
				//放行
				return invoke.invoke();
			}
			
		}

		//=============================================
		
		if (admin != null) {
			String resCodeStr = admin.getResCode();
			if (ValidateUtils.stringValidate(resCodeStr)) {
				String[] strArr = DataProcessUtils.convertStrToArr(resCodeStr);
				for (int i = 0; i < strArr.length; i++) {
					String str = strArr[i];
					if (str != null) {
						long code = Long.parseLong(str);
						if ((code & rescode) != 0) {
							return invoke.invoke();
						}
					}
				}
			}
		}

		if (user != null) {
			String resCodeStr = user.getResCode();
			if (ValidateUtils.stringValidate(resCodeStr)) {
				String[] strArr = DataProcessUtils.convertStrToArr(resCodeStr);
				for (int i = 0; i < strArr.length; i++) {
					String str = strArr[i];
					if (str != null && !("null".equals(str))) {
						long code = Long.parseLong(str);
						if ((code & rescode) != 0) {
							return invoke.invoke();
						}
					}
				}
			}
		}

		if (action instanceof ValidationAware) {
			ValidationAware va = (ValidationAware) action;
			va.addActionError("此用户权限访问资源！");
		}

		// 2.根据resCode与当前放问的支援权限进行与运算， 为0返回，没有权限， 不为0放行
		return "toGlobalMess";
	}

}
