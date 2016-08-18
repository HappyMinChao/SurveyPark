package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.base.i.BaseDao;

public interface AdminDao extends BaseDao<Admin> {

	public abstract Admin getEntityByIdAndPwd(String adminName, String adminPwd);

	public abstract int getTotalRecord();

	public abstract List<Admin> getAdminListPage(Integer currentPageNo,
			Integer pageSize);

	public abstract void deleteAuthByRoleIdInner(Integer adminId);

	public abstract void doBatchSave(Integer adminId, List<Integer> roleIdList);

	List<Admin> getAllAdmin();

}
