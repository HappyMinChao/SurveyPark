package com.atguigu.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.ResourceDao;
import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseServcieImpl;

@Service
public class ResourceServiceImpl extends BaseServcieImpl<Resource> implements
		ResourceService{
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> getAllResourcesList() {
		return resourceDao.getAllResourcesList();
	}

	@Override
	public Integer getMaxResPos() {
		return resourceDao.getMaxResPos();
	}

	@Override
	public Long getMaxResCode(Integer resPos) {
		Long maxPos = resourceDao.getMaxResCode(resPos);
		return maxPos;
	}

	@Override
	public Resource getResourceByActionName(String actionName) {
		
		return resourceDao.getResourceByActionName(actionName);
	}

	@Override
	public void doBatchDelete(List<Integer> resIdList) {
		
		List<Object[]> params = new ArrayList<>();
		
		String sql = "DELETE FROM resources WHERE resource_id = ?";
		
		for(int i = 0 ; i < resIdList.size() ; i ++){
			Object[] param = new Object[1];
			param[0] = resIdList.get(i);
			
			params.add(param);
		}
		
		resourceDao.doBatchWork(sql, params);
	}

	@Override
	public List<Integer> getResIDInner(Integer authorityId) {
		return resourceDao.getResIDInner(authorityId);
	}
	
}
