package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.i.BaseService;

@Transactional
public interface ResourceService extends BaseService<Resource> {

	public abstract List<Resource> getAllResourcesList();

	public abstract Integer getMaxResPos();

	public abstract Long getMaxResCode(Integer resPos);

	public abstract Resource getResourceByActionName(String actionName);

	public abstract void doBatchDelete(List<Integer> resIdList);

	public abstract List<Integer> getResIDInner(Integer authorityId);

}
