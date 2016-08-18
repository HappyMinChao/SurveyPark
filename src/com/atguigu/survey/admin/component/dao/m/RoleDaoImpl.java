package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.RoleDao;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.base.m.BaseDaoImpl;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	
	@Override
	public void deleteAllInner() {
		String sql = "DELETE FROM role_auth_inner";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int getTotalRecord() {
		String sql = "SELECT COUNT(*) FROM roles";
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}

	@Override
	public List<Role> getRoleListPage(Integer currentPageNo, Integer pageSize) {
		String hql = "From Role";
		List list = this.getSession().createQuery(hql)
									.setFirstResult((currentPageNo-1) * pageSize)
									.setMaxResults(pageSize)
									.list();
		return list;
	}

	@Override
	public void deleteAuthByRoleIdInner(Integer roleId) {
		String sql = "DELETE FROM role_auth_inner WHERE ROLE_ID = ?";
		this.getSession().createSQLQuery(sql).setInteger(0, roleId).executeUpdate();
		
	}

	@Override
	public List<Integer> getInnerRoleIDByAdminId(Integer adminId) {
		String sql = "SELECT ROLE_ID FROM admin_role_inner WHERE ADMIN_ID = ?";
		List list = this.getSession().createSQLQuery(sql).setInteger(0, adminId).list();
		return list;
	}

	@Override
	public List getAllRole() {
		String hql = "From Role";
		return this.getSession().createQuery(hql).list();
	}
	@Override
	public Set<Role> initAllRole(Set<Role> set){
		
		for (Role role : set) {
			Set<Authority> authoritySet = role.getAuthoritySet();
			Hibernate.initialize(authoritySet);
			for (Authority authority : authoritySet) {
				Set<Resource> resourceSet = authority.getResourceSet();
				Hibernate.initialize(resourceSet);
			}
		}
		
		return set;
	}
	                                  
	@Override
	public Role getRoleByName(String roleName) {
		String hql = "From Role r where r.roleName=?";
		Role role = (Role) this.getSession().createQuery(hql).setString(0, roleName).uniqueResult();
		return role;
	}

}
