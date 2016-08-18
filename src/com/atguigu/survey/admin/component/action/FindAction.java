package com.atguigu.survey.admin.component.action;

import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.m.BaseAction;

public class FindAction extends BaseAction<Admin> {

	private static final long serialVersionUID = 1L;
	
	public String toFindPage(){
		
		return "toFindPage";
	}

}
