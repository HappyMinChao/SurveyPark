package com.atguigu.survey.base.i;

import java.util.List;

/**
 * Service基类
 */
public interface BaseService<T> {
	
	/**
	 * 保存一个实体对象
	 * @param t
	 */
	void saveEntity(T t);
	
	/**
	 * 根据实体删除对象
	 * @param t
	 */
	void deleteEntity(T t);
	
	/**
	 * 根据实体对象更新数据库表中的记录
	 * @param t
	 */
	void updateEntity(T t);
	
	/**
	 * 根据OID的值查询对应的实体对象
	 * @param entityId
	 * @return
	 */
	T getEntityById(Integer entityId);
	
	/**
	 * 根据实体类对象的类型查询全部数据
	 * @return
	 */
	List<T> getEntityList();

}
