package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.AuthorityDao;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.m.BaseDaoImpl;

@Repository
public class AuthorityDaoImpl extends BaseDaoImpl<Authority> implements
		AuthorityDao {

	@Override
	public List<Authority> getAuthorityListPage(Integer currentPage, Integer pageSize) {
		String hql = "From Authority";
		List list = this.getSession().createQuery(hql).setFirstResult((currentPage-1) * pageSize).setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public int getTotalAuthorityCount() {
		String sql = "SELECT COUNT(*) FROM authoritys";
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}

	@Override
	public List getAllAuthority() {
		String hql = "From Authority";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public void deleteInnerByAuthId(Integer authorityId) {
		String sql = "delete from auth_res_inner where authority_id = ?";
		this.getSession().createSQLQuery(sql).setInteger(0, authorityId).executeUpdate();
	}

	@Override
	public List<Integer> getAuthIDInner(Integer roleId) {
		String sql = "SELECT AUTHORITY_ID FROM role_auth_inner WHERE ROLE_ID = ?";
		List list = this.getSession().createSQLQuery(sql).setInteger(0, roleId).list();
		return list;
	}
}
