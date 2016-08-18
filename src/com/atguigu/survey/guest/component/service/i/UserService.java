package com.atguigu.survey.guest.component.service.i;

import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.base.i.BaseService;

public interface UserService extends BaseService<User>{

	/**
	 * 检查用户名是否可用
	 * @param userName
	 * @return
	 * 		true:用户名已经被占用
	 * 		false:用户名没有被占用
	 */
	boolean regist(User user);

	User login(User t);

	boolean vip(int vipDays, User t);

}
