package com.atguigu.survey.guest.component.dao.m;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.UserDao;
import com.atguigu.survey.guest.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
	@Override
	public List<User> getAllUser(){
		String hql = "From User";
		List<User> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	@Override
	public boolean checkUserName(String userName) {
		
		String hql = "select count(*) from User u where u.userName=?";
		
		long count = (long) this.getSession().createQuery(hql).setString(0, userName).uniqueResult();
		
		return (count > 0);
	}

	@Override
	public User login(User t) {
		
		String hql = "From User u where u.userName=? and u.userPwd=? ";
		
		
		
		return (User) this.getSession()
						  .createQuery(hql)
						  .setString(0, t.getUserName())
						  .setString(1, t.getUserPwd())
						  .uniqueResult();
	}

	
	
}
