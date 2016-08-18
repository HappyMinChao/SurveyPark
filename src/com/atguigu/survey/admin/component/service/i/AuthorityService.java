package com.atguigu.survey.admin.component.service.i;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.i.BaseService;
import com.atguigu.survey.guest.model.Page;

@Transactional
public interface AuthorityService extends BaseService<Authority> {

	Page getAuthorityPage( String currentPageNo,
			String pageSize);

	void doBatchDelete(List<Integer> authIdList);

	void doBatchSave(List<Integer> resIdList, Integer authorityId);

	List getAllAuthority();

	void deleteInnerByAuthId(Integer authorityId);

	List<Integer> getAuthIDInner(Integer roleId);

}
