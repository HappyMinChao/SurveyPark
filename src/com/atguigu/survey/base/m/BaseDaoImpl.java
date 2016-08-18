package com.atguigu.survey.base.m;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.utils.ValidateUtils;

public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> entityType;
	
	public BaseDaoImpl() {
		
		//精简版实现方式
		//this.entityType = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		//1.证明：父类中的this关键字在子类对象中指向的是子类对象
		//System.out.println(this.getClass().getName());
		
		//2.具体的泛型参数是传给了父类类型，所以要先把父类类型拿到
		Class<?> superclass = this.getClass().getSuperclass();
		//System.out.println(superclass.getName());
		
		//3.但是上述操作获取到的父类类型并没有包含泛型参数，所以需要更加精确的获取带有泛型参数的父类类型
		//Type接口：类型——如何理解类型？能够修饰变量的就是类型
		//不带泛型参数的类型：String、Object等等
		//带泛型参数的类型：BaseDaoImpl<User>、List<String>
		Type type = this.getClass().getGenericSuperclass();
		
		//4.判断type是否是一个“带参数的类型——ParameterizedType”
		if(type instanceof ParameterizedType) {
			//5.转换类型
			ParameterizedType pt = (ParameterizedType) type;
			
			//6.获取实际的类型参数
			//Map<String,String>——泛型参数可以传多个
			Type[] actualTypeArguments = pt.getActualTypeArguments();
			
			//7.获取目标类型
			Type targetType = actualTypeArguments[0];
			
			//8.赋值给entityType
			this.entityType = (Class<T>) targetType;
		}
	}
	
	@Autowired
	private SessionFactory factory;
	
	/**
	 * 很难理解！！！！！！！！！！！
	 */
	
	public void doBatchWork(final String sql, final List<Object[]> params){
		this.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				
				if(ValidateUtils.validateCollection(params)) {
					//遍历List得到的对象对应SQL语句的一次执行
					for(int i = 0; i < params.size(); i++) {
						Object[] paramArr = params.get(i);
						
						if(!ValidateUtils.arrayValidate(paramArr)) continue;
						
						for (int j = 0; j < paramArr.length; j++) {
							
							//给每一条SQL语句设置占位符参数,
							prepareStatement.setObject(j+1, paramArr[j]);
							
						} // for j 
						
						//积攒SQL语句
						prepareStatement.addBatch();
					} // for i 
					
					//批量执行SQL语句 这是JDBC原声支持的方法，hibernate没有提供支持
					//批处理只能处理更新工作，不能批量处理查询
					prepareStatement.executeBatch();
				}// if 
				
				prepareStatement.close();
				
			}//excute
		});
	}
	
	//获取Session对象的统一方法
	public Session getSession() {
		//根据具体情况进行灵活的切换
		//单独测试Dao：openSession()
		//测试Service：getCurrentSession()
		return factory.getCurrentSession();
	}
	
	public void saveEntity(T t) {
		
		//1.获取Session对象
		Session session = getSession();
		
		//2.执行保存
		session.save(t);
	}

	@Override
	public void deleteEntity(T t) {
		
		//1.获取Session对象
		Session session = getSession();
		
		//2.执行删除
		session.delete(t);
		
	}

	@Override
	public void updateEntity(T t) {
		
		//1.获取Session对象
		Session session = getSession();
		
		//2.执行更新
		session.update(t);
		
	}

	@Override
	public T getEntityById(Integer entityId) {
		
		//1.获取Session对象
		Session session = getSession();
		
		//2.执行查询
		T t = (T) session.get(entityType, entityId);
		
		return t;
	}

	@Override
	public List<T> getEntityList() {
		
		//1.获取Session对象
		Session session = getSession();
		
		//2.创建Criteria对象执行查询  criteria模糊查询
		List<T> list = session.createCriteria(entityType).list();
		
		return list;
	}

	@Override
	public List<T> findByCondition(String hql,String condition) {
		List<T> list = this.getSession().createQuery(hql).setString(0, condition).list();
		return list;
	}


}