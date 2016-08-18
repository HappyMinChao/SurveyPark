package com.atguigu.survey.admin.component.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AdminService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction<Admin> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	private String currentPageNo;
	private List<Integer> adminIdList;
	private List<Integer> roleIdList;
	

	//========================Action方法区==========================
	
	//同一生成权限码
	public String calculateCode(){
		adminService.calculateCode();
		return "adminLoginSuccess";
	}
	
	//分配角色
	public String allocateAuthority(){
		adminService.deleteAuthByRoleIdInner(t.getAdminId());
		adminService.doBatchSave(t.getAdminId(), roleIdList);
		
		return "adminLoginSuccess";
	}
	//去往分配角色页面
	public String toAllocate(){
		List<Integer> roleIdValueList = roleService.getInnerRoleIDByAdminId(t.getAdminId());
		List roleList = roleService.getAllRole();
		reqMap.put("roleIdValueList", roleIdValueList);
		reqMap.put("roleList", roleList);
		return "toAllocateRolePage";
	}
	
	//退出登录
	public String logout(){
		sessionMap.remove(GlobalNames.LOGIN_ADMIN);
		return "toAdminLoginPage";
	}
	
	//批量生成admin账号
	public String generateAdmin(){
		List<Admin> adminList = adminService.generateAdminList();
		reqMap.put(GlobalNames.NEW_ADMIN_LIST, adminList);
		
		return "toNewAdminsPage";
	}
	
	//批量删除admin
	public String delete() {
		adminService.doBatchDelete(adminIdList);
		return "toShowListAction";
	}
	
	public String showAdminList() {
		String pageSize = "10";
		Page page = adminService.getAdminListPage(currentPageNo, pageSize);
		reqMap.put(GlobalNames.PAGE, page);
		return "toShowAdminPage";
	}
	
	public String login(){
		
		Admin admin = adminService.login(t);
		
		if(null == admin){
			addActionError("用户名、密码错误！");
			return "toAdminLoginPage";
		}else{
			sessionMap.put(GlobalNames.LOGIN_ADMIN, admin);
			//return "showAllSurvey";///待完善。 登录完成页面，和这的返回结果
			return "adminLoginSuccess";
		}
	
	}

	public String toAdminPage(){
		return "toAdminLoginPage";
	}
	
	public String execute()
	{
		return "success";
	}

	//===================set/get方法区================================

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public List<Integer> getAdminIdList() {
		return adminIdList;
	}

	public void setAdminIdList(List<Integer> adminIdList) {
		this.adminIdList = adminIdList;
	}
	
	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

}
