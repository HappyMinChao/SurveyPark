package com.atguigu.survey.admin.component.dao.i;

import java.util.List;

import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.i.BaseDao;

public interface AuthorityDao extends BaseDao<Authority> {

	List<Authority> getAuthorityListPage(Integer currentPage, Integer pageSize);

	int getTotalAuthorityCount();

	List getAllAuthority();

	void deleteInnerByAuthId(Integer authorityId);

	List<Integer> getAuthIDInner(Integer roleId);

}
