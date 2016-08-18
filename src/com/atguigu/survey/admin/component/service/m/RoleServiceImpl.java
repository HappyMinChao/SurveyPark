package com.atguigu.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.RoleDao;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.model.Page;

@Service
public class RoleServiceImpl extends BaseServcieImpl<Role> implements RoleService{
	@Autowired
	private RoleDao roleDao;

	@Override
	public Page getRoleListPage(String currentPageNo, String pageSize) {
		int totalRecord = roleDao.getTotalRecord();
		
		Page<Role> page = new Page<Role>(currentPageNo, pageSize, totalRecord);
		List<Role> list = roleDao.getRoleListPage(page.getCurrentPageNo(),page.getPageSize());
		page.setList(list);
		return page;
	}
	
	@Override
	public void doBatchDelete(List<Integer> roleIdList) {
		
		String sql = "delete from roles where role_id=?";
		
		List<Object[]> params = new ArrayList<>();
		
		for (int i = 0 ; i < roleIdList.size() ; i++) {
			Object[] param = new Object[1];
			param[0] = roleIdList.get(i);
			
			params.add(param);
		}
		roleDao.doBatchWork(sql, params);
	}

	@Override
	public void doBatchSave(Integer roleId, List<Integer> authIdList) {

		String sql = "INSERT INTO role_auth_inner(ROLE_ID, AUTHORITY_ID) VALUES(?,?)";
		
		List<Object[]> params = new ArrayList<>();
		
		for (int i = 0 ; i < authIdList.size() ; i++) {
			Object[] param = new Object[2];
			param[0] = roleId;
			param[1] = authIdList.get(i);
			
			params.add(param);
		}
		roleDao.doBatchWork(sql, params);
		
	}

	@Override
	public void deleteAuthByRoleIdInner(Integer roleId) {
		roleDao.deleteAuthByRoleIdInner(roleId);
		
	}

	@Override
	public List<Integer> getInnerRoleIDByAdminId(Integer adminId) {
	
		return roleDao.getInnerRoleIDByAdminId(adminId);
	}

	@Override
	public List getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAllRole() ;
	}

	@Override
	public Role getRoleByName(String string) {
		
		return roleDao.getRoleByName(string);
	}

	@Override
	public void initAllRole(Set<Role> roleSet) {
		roleDao.initAllRole(roleSet);
	}
}
