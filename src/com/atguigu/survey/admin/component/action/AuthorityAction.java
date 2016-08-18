package com.atguigu.survey.admin.component.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class AuthorityAction extends BaseAction<Authority> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private ResourceService resourceService;

	private String currentPageNo;
	private List<Integer> authIdList;
	private List<Integer> resIdList;
	private Integer authorityId;
	
	//=======================Action方法区================================
	
	public String allocateResource(){
		authorityService.deleteInnerByAuthId(authorityId);
		authorityService.doBatchSave(resIdList,authorityId);
		return "adminLoginSuccess";
	}
	
	public String toAllocate(){
		List<Integer> resIdValueList = resourceService.getResIDInner(authorityId);
		List<Resource> list = resourceService.getAllResourcesList();
		reqMap.put("resList", list);
		reqMap.put("resIdValueList", resIdValueList);
		return "toAllocatePage";
	}
	
	public String delete(){
		authorityService.doBatchDelete(authIdList);
		return "toShowListAction";
	}
	

	public String authorityList(){
		String pageSize = "10";
		//1.获取出分页的Authority
		@SuppressWarnings("unchecked")
		Page<Authority> page = authorityService.getAuthorityPage(currentPageNo,pageSize);
		
		reqMap.put(GlobalNames.PAGE, page);
				
		return "toShowAuthoritiesPage";
	}
	
	public String save(){
		authorityService.saveEntity(t);
		return "toShowListAction";
	}
	
	
	
	//=============================get/set方法区=========================================
	
	

	public Integer getAuthorityId() {
		return authorityId;
	}
	public List<Integer> getResIdList() {
		return resIdList;
	}
	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public String toCreatePage(){		
		return "toCreateAuthorityPage";
	}

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public List<Integer> getAuthIdList() {
		return authIdList;
	}

	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
	
}
