package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.i.BaseDao;

public interface ResourceDao extends BaseDao<Resource> {

	public abstract List<Resource> getAllResourcesList();

	public abstract Integer getMaxResPos();

	public abstract Long getMaxResCode(Integer resPos);

	public abstract Resource getResourceByActionName(String actionName);

	public abstract List<Integer> getResIDInner(Integer authorityId);


}
