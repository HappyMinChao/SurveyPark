package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

@Transactional
public interface AdminService extends BaseService<Admin>{

	public abstract Admin login(Admin t);

	public abstract Page getAdminListPage(String currentPageNo, String pageSize);

	void doBatchDelete(List<Integer> roleIdList);

	public abstract List<Admin> generateAdminList();

	public abstract void deleteAuthByRoleIdInner(Integer adminId);

	public abstract void doBatchSave(Integer adminId, List<Integer> roleIdList);

	public abstract void calculateCode();


}
