package com.atguigu.survey.admin.component.dao.i;

import java.util.List;
import java.util.Set;

import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.i.BaseDao;

public interface RoleDao extends BaseDao<Role> {

	int getTotalRecord();

	List<Role> getRoleListPage(Integer currentPageNo, Integer pageSize);

	void deleteAllInner();

	void deleteAuthByRoleIdInner(Integer roleId);

	List<Integer> getInnerRoleIDByAdminId(Integer adminId);

	List getAllRole();

	Set<Role> initAllRole(Set<Role> set);

	Role getRoleByName(String string);

}
