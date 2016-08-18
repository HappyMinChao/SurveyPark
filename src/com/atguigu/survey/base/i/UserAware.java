package com.atguigu.survey.base.i;

import com.atguigu.survey.guest.entity.User;

/**
 * 让需要User对象的目标Action类实现这个接口，在LoginInterceptor中，为其注入
 *
 */
public interface UserAware {
	
	void setUser(User user);

}
