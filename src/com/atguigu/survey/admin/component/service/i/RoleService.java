package com.atguigu.survey.admin.component.service.i;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

@Transactional
public interface RoleService extends BaseService<Role> {

	Page getRoleListPage(String currentPageNo, String pageSize);

	void doBatchDelete(List<Integer> roleIdList);

	void doBatchSave(Integer roleId, List<Integer> authIdList);

	void deleteAuthByRoleIdInner(Integer roleId);

	List<Integer> getInnerRoleIDByAdminId(Integer adminId);

	List getAllRole();

	Role getRoleByName(String string);

	void initAllRole(Set<Role> roleSet);

}
