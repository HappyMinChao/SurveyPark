package com.atguigu.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ResourceService resourceService;
	
	private Integer resourceId;

	private String resourceName;
	
	private List<Integer> resIdList;
	
	//==================Action方法区============================
	
	public String delete(){
		resourceService.doBatchDelete(resIdList);
		return "deleteSuccess";
	}
	
	//json的返回map， 提供的Map
	public Map<String,String> getMessage() {
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");
		
		return map;
	}
	
	public void prepareUpdate(){
		this.t = resourceService.getEntityById(resourceId);
	}

	public String update(){
		
		resourceService.updateEntity(t);
		
		return "updateSuccess";
	}
	
	public String showAllResources(){
		
		//1.查询出所有的Resource
		List<Resource> resList = resourceService.getAllResourcesList();
		//2.放在request域中跳转
		reqMap.put(GlobalNames.RESOURCE, resList);
		
		return "toShowAllResPage";
	}

	//=======================get/set方法区=======================
	
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<Integer> getResIdList() {
		return resIdList;
	}

	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
	
}
