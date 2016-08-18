package com.atguigu.survey.admin.component.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
	private String currentPageNo;
	private List<Integer> roleIdList;
	private List<Integer> authIdList;
	
	
	//========================Action方法区======================
	
	
	public String allocateAuthority(){
		roleService.deleteAuthByRoleIdInner(t.getRoleId());
		roleService.doBatchSave(t.getRoleId(), authIdList);
		
		return "adminLoginSuccess";
	}
	public String toAllocate(){
		List<Integer> authIdValueList = authorityService.getAuthIDInner(t.getRoleId());
		List list = authorityService.getAllAuthority();
		reqMap.put("authIdValueList", authIdValueList);
		reqMap.put("authList", list);
		return "toAllocateAuthPage";
	}
	public String save(){
		roleService.saveEntity(t);
		return "toShowListAction";
	}
	
	public String toSave(){
		return "toSavePage";
	}
	

	public String delete() {
		roleService.doBatchDelete(roleIdList);
		return "toShowListAction";
	}

	public String showRoleList() {
		String pageSize = "10";
		Page page = roleService.getRoleListPage(currentPageNo, pageSize);
		reqMap.put(GlobalNames.PAGE, page);
		return "toShowRolePage";
	}

	// ==================get/set方法区========================

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	public List<Integer> getAuthIdList() {
		return authIdList;
	}
	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
	
}
