package com.atguigu.survey.admin.component.dao.m;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.admin.component.dao.i.ResourceDao;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.base.m.BaseDaoImpl;

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	@Override
	public List<Resource> getAllResourcesList() {
		String hql = "From Resource r order by r.resourceName";
		List ResourceList = this.getSession().createQuery(hql).list();
		return ResourceList;
	}

	@Override
	public Integer getMaxResPos() {
		String sql = "SELECT MAX(res_pos) FROM resources";
		Integer count = (Integer)this.getSession().createSQLQuery(sql).uniqueResult();
		return count;
	}

	@Override
	public Long getMaxResCode(Integer resPos) {
		String sql = "SELECT MAX(res_code) FROM resources WHERE res_pos = ?";
		BigInteger maxCode = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, resPos).uniqueResult();
		return maxCode.longValue();
	}

	@Override
	public Resource getResourceByActionName(String actionName) {
		String hql = "from Resource r where r.actionName=?";
		Resource resource = (Resource) this.getSession().createQuery(hql).setString(0, actionName).uniqueResult();
		return resource;
	}

	@Override
	public List<Integer> getResIDInner(Integer authorityId) {
		String sql = "SELECT RESOURCE_ID FROM auth_res_inner WHERE AUTHORITY_ID = ?";
		List list = this.getSession().createSQLQuery(sql).setInteger(0, authorityId).list();
		return list;
	}

	

}
