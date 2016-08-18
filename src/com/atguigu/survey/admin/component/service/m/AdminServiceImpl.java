package com.atguigu.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.AdminDao;
import com.atguigu.survey.admin.component.dao.i.ResourceDao;
import com.atguigu.survey.admin.component.dao.i.RoleDao;
import com.atguigu.survey.admin.component.service.i.AdminService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.UserDao;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.ValidateUtils;

@Service
public class AdminServiceImpl extends BaseServcieImpl<Admin> implements AdminService {

	@Autowired
	AdminDao adminDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleService;
	@Autowired
	ResourceDao resourceDao;
	
	@Override
	public Admin login(Admin admin) {
		
		if("admin".equals(admin.getAdminName()) 
				&& "admin".equals(admin.getAdminPwd())){
			return admin;
		}else{
			Admin result = adminDao.getEntityByIdAndPwd(admin.getAdminName(),admin.getAdminPwd());
			return result;
		}
		
	}

	@Override
	public Page getAdminListPage(String currentPageNo, String pageSize) {
		int totalRecord = adminDao.getTotalRecord();
		
		Page<Admin> page = new Page<Admin>(currentPageNo, pageSize, totalRecord);
		List<Admin> list = adminDao.getAdminListPage(page.getCurrentPageNo(),page.getPageSize());
		page.setList(list);
		return page;
	}
	
	@Override
	public void doBatchDelete(List<Integer> adminIdList) {
		
		String sql = "delete from admins where admin_id=?";
		
		List<Object[]> params = new ArrayList<>();
		
		for (int i = 0 ; i < adminIdList.size() ; i++) {
			Object[] param = new Object[1];
			param[0] = adminIdList.get(i);
			
			params.add(param);
		}
		adminDao.doBatchWork(sql, params);
	}

	@Override
	public List<Admin> generateAdminList() {
		List<Admin> list = new ArrayList<>();
		for(int i = 0 ; i <20 ; i++){
			String adminName = DataProcessUtils.generateRandomString(6);
			String adminPwd = DataProcessUtils.generateRandomString(6);
			
			Admin admin = new Admin(null, adminName, adminPwd);
			
			adminDao.saveEntity(admin);
			list.add(admin);
		}
		
		return list;
	}

	@Override
	public void deleteAuthByRoleIdInner(Integer adminId) {
		// TODO Auto-generated method stub
		adminDao.deleteAuthByRoleIdInner(adminId);
		
	}

	@Override
	public void doBatchSave(Integer adminId, List<Integer> roleIdList) {
		// TODO Auto-generated method stub
		adminDao.doBatchSave(adminId, roleIdList);
	}

	@Override
	public void calculateCode() {
		//首先获取到所有的Amin用户
		List<Admin> allAdmin = adminDao.getAllAdmin();
		if(!ValidateUtils.validateCollection(allAdmin)){
			return ;
		}
		
		//获取到最大权限位
		Integer maxResPos = resourceDao.getMaxResPos();
		
		if(maxResPos == null) {
			//等于null说明数据库中还没有资源
			return ;
		}
		
		
		
		
		//获取到所有的user用户
		for (Admin admin : allAdmin) {
			Set<Role> roleSet = admin.getRoleSet();
			
			String resCodesStr = DataProcessUtils.calculateResCode(roleSet, maxResPos);
			
			admin.setResCode(resCodesStr);
			adminDao.updateEntity(admin);
		}//adminSet
		
		//user 不能同意计算权限码， 他是在付费，过期，或者登陆的时候进行计算的。
		//计算他们的权限吗并保存
		/*List<User> allUser = userDao.getAllUser();
		
		for (User user : allUser) {
			Set<Role> roleSet = user.getRoleSet();
			roleService.initAllRole(roleSet);
			
			String resCodesStr = DataProcessUtils.calculateResCode(roleSet, maxResPos);
			
			user.setResCode(resCodesStr);
			userDao.updateEntity(user);
			
		}*/
	}

	

}
