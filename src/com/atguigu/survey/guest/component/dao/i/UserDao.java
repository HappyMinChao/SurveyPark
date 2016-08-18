package com.atguigu.survey.guest.component.dao.i;

import java.util.List;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.User;

public interface UserDao extends BaseDao<User>{

	boolean checkUserName(String userName);

	User login(User t);

	List<User> getAllUser();
	
}
