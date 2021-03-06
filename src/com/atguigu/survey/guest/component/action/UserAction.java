package com.atguigu.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.UserService;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	//*******************************成员变量区*************************************
	private static final long serialVersionUID = 7076219122396821583L;
	
	@Autowired
	private UserService userService;
	
	private Integer payAmount;
	private Integer vipDays;
	
	//*******************************Action方法区*************************************
	public String vip() {
		
		this.t = (User) sessionMap.get(GlobalNames.LOGIN_USER);
		
		boolean vipSuccess = userService.vip(vipDays,this.t);
		
		if(vipSuccess) {
			return "toMyCenterPage";
		}else{
			this.addActionError("您的余额不足，请充值后再续费！");
			return "toPayPage";
		}
		
	}
	
	public String pay() {
		
		//注意：此时this.t是一个仅有userId的空的对象，如果使用这样的对象来进行更新，就会把数据库中其他数据置空
		//应该使用一个包含完整数据的User对象：从Session域中获取
		this.t = (User) sessionMap.get(GlobalNames.LOGIN_USER);
		
		//设置充值后的余额
		this.t.setBalance(this.t.getBalance()+payAmount);
		
		//更新用户余额
		userService.updateEntity(t);
		
		return "toMyCenterPage";
	}
	
	public String logout() {
		this.sessionMap.remove(GlobalNames.LOGIN_USER);
		return "logoutSuccess";
	}
	
	public String login() {
		
		//1.检查用户名、密码是否正确
		User user = userService.login(this.t);
		
		if(user != null) {
			//2.将User对象保存到Session域中
			this.sessionMap.put(GlobalNames.LOGIN_USER, user);
			
			//3.如果正确，则跳转到首页
			return "loginSuccess";
			
		}else{
			//4.如果错误，则返回登录页面，并显示错误消息
			this.addActionError("用户名或密码不正确，请重新输入！");
			
			return "loginError";
		}
		
	}
	
	public String register() {
		
		//1.检查用户名是否可用
		boolean exists = userService.regist(t);
		
		if(exists) {
			//2.如果用户名不可用则回到注册表单页面
			addActionError("用户名已经被占用了，请重新输入！");
			
			return "registError";
			
		}else{
			//3.如果用户名可用则前往登录页面
			return "registSuccess";
			
		}
		
	}
	
	//*******************************getXxx()、setXxx()方法区*************************************
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	
	public void setVipDays(Integer vipDays) {
		this.vipDays = vipDays;
	}
}
