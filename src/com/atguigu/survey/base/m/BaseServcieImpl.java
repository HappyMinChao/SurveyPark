package com.atguigu.survey.base.m;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.base.i.BaseService;

@Transactional
public class BaseServcieImpl<T> implements BaseService<T>{
	
	private Class entity;
	
	public BaseServcieImpl() {
		/*ParameterizedType paramSuperClass = (ParameterizedType)this.getClass().getGenericSuperclass();
		entity = (Class)paramSuperClass.getActualTypeArguments()[0];*/
	}
	
	/**
	 * sql语句：
	 * 
	 * SELECT * FROM roles 
		WHERE role_name LIKE '%用户%'
		ORDER BY role_name DESC , role_id ASC;
	 *@Description: 通过条件进行模糊查询
	 *@Author:du_minchao
	 *@CreateDate:2016年3月4日
	 *@Parameters:@return
	 *@return:List<T>
	 */
	
	public List<T> findByCondition(String condition){
		
		String hql = "From " +entity.getSimpleName()+" o where 1=1 and o."+getCapitalName(entity.getSimpleName())+"Name "
				+ " order by "+getCapitalName(entity.getSimpleName())+"Id desc";
		
		List<T> list = baseDao.findByCondition(hql,condition);
		return list;
	}

	public String getCapitalName(String simpleName) {
		char charAt = simpleName.charAt(0);
		charAt = (char)(charAt + 32);
		simpleName = charAt + simpleName.substring(1);
		return simpleName;
	}

	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public T getEntityById(Integer entityId) {
		return baseDao.getEntityById(entityId);
	}

	@Override
	public List<T> getEntityList() {
		return baseDao.getEntityList();
	}

}
