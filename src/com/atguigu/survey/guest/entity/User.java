package com.atguigu.survey.guest.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.atguigu.survey.admin.entity.Role;

public class User {
	
//	[1]OID：对应数据库表中的主键
	private Integer userId;
	
//	[2]用户名
	private String userName;
	
//	[3]密码
	private String userPwd;
	
//	[4]昵称
	private String nickName;
	
//	[5]余额
	private int balance;
	
//	[6]会员状态
	private boolean payStatus;
	
//	[7]会员到期的截止日期：以时间戳形式进行保存
	private long endTime;
	
//	[8]Email
	private String email;
	
	private String resCode;
	
	private Set<Role> roleSet;
	
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", nickName=" + nickName
				+ ", balance=" + balance + ", payStatus=" + payStatus
				+ ", endTime=" + endTime + ", email=" + email + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//返回格式化后的截止日期
	public String getEndDate() {
		
		Date date = new Date(endTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");
		
		return format.format(date);
	}
	
	//提供一个方法用于在页面上显示当前付费用户会员剩余天数
	public int getLeftDays() {
		
		if(!payStatus) return 0;
		
		//1.获取当前系统时间的时间戳
		long currentTime = new Date().getTime();
		
		//2.用当前截止时间减去currentTime
		long leftMillionSeconds = endTime - currentTime;
		
		//3.计算天数
		int days = (int)(leftMillionSeconds / 1000 / 60 / 60 / 24);
		
		return days;
	}
	
}
