package com.atguigu.survey.admin.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.admin.component.dao.i.AuthorityDao;
import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.model.Page;

@Service
public class AuthorityServliceImpl extends BaseServcieImpl<Authority> implements
		AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public Page getAuthorityPage(String currentPageNo,
			String pageSize) {
		int totalRecord = authorityDao.getTotalAuthorityCount();
		
		Page page = new Page<>(currentPageNo, pageSize, totalRecord);
		List<Authority> list = authorityDao.getAuthorityListPage(page.getCurrentPageNo(),page.getPageSize());
		page.setList(list);
		return page;
	}

	@Override
	public void doBatchDelete(List<Integer> authIdList) {
		
		String sql = "delete from authoritys where authority_id=?";
		
		List<Object[]> params = new ArrayList<>();
		
		for (int i = 0 ; i < authIdList.size() ; i++) {
			Object[] param = new Object[1];
			param[0] = authIdList.get(i);
			
			params.add(param);
		}
		authorityDao.doBatchWork(sql, params);
	}

	@Override
	public void doBatchSave(List<Integer> resIdList, Integer authorityId) {
		
		List<Object[]> params = new ArrayList<>();
		
		for(int i = 0 ; i < resIdList.size(); i++){
			Object[] param = new Object[2];
			param[0] = authorityId;
			param[1] = resIdList.get(i);
			
			params.add(param);
		}
		
		String sql = "INSERT INTO auth_res_inner(AUTHORITY_ID, RESOURCE_ID) VALUES(?,?)";
		authorityDao.doBatchWork(sql, params);
		
	}

	@Override
	public List getAllAuthority() {
		
		return authorityDao.getAllAuthority();
	}

	@Override
	public void deleteInnerByAuthId(Integer authorityId) {
		authorityDao.deleteInnerByAuthId(authorityId);
		
	}

	@Override
	public List<Integer> getAuthIDInner(Integer roleId) {
		return authorityDao.getAuthIDInner(roleId);
	}
	
	
}
