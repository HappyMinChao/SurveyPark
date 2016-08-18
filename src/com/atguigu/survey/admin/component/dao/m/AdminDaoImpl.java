package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.AdminDao;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseDaoImpl;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
	@Override
	public List<Admin> getAllAdmin(){
		String hql = "From Admin";
		List<Admin> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	@Override
	public Admin getEntityByIdAndPwd(String adminName, String adminPwd) {
		String hql = "from Admin a where a.adminName=? and a.adminPwd=?";
		Admin admin = (Admin)this.getSession()
								.createQuery(hql)
								.setString(0, adminName)
								.setString(1, adminPwd)
								.uniqueResult();
		return admin;
	}

	@Override
	public int getTotalRecord() {
		String sql = "SELECT COUNT(*) FROM admins";
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}

	@Override
	public List<Admin> getAdminListPage(Integer currentPageNo, Integer pageSize) {
		String hql = "From Admin";
		List list = this.getSession().createQuery(hql)
									.setFirstResult((currentPageNo-1) * pageSize)
									.setMaxResults(pageSize)
									.list();
		return list;
	}

	@Override
	public void deleteAuthByRoleIdInner(Integer adminId) {
		String sql = "DELETE FROM admin_role_inner WHERE ADMIN_ID = ?";
		this.getSession().createSQLQuery(sql).setInteger(0, adminId).executeUpdate();
		
	}

	@Override
	public void doBatchSave(Integer adminId, List<Integer> roleIdList) {
		String sql = "INSERT INTO admin_role_inner(ADMIN_ID,ROLE_ID) VALUES(?,?)";
		
		List<Object[]> params = new ArrayList<>();
		
		for(int i = 0; i < roleIdList.size(); i++ ){
			Object[] param = new Object[2] ;
			param[0] = adminId;
			param[1] = roleIdList.get(i);
			
			params.add(param);
			
		}
		
		this.doBatchWork(sql, params);
		
	}

	
	
	
}
