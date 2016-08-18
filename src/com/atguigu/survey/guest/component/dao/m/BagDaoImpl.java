package com.atguigu.survey.guest.component.dao.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atguigu.survey.base.m.BaseDaoImpl;
import com.atguigu.survey.guest.component.dao.i.BagDao;
import com.atguigu.survey.guest.entity.Bag;

@Repository
public class BagDaoImpl extends BaseDaoImpl<Bag> implements BagDao {

	@Override
	public void updateBagOrder(List<Bag> bagList) {
		//1.准备SQL语句
		String sql = "UPDATE bags SET bag_order=? WHERE BAG_ID=?";
		
		//2.将bagList转换成List<Object[]> params
		List<Object[]> params = new ArrayList<>();
		for(int i = 0; i < bagList.size(); i++) {
			Bag bag = bagList.get(i);
			Object[] param = new Object[2];
			param[0] = bag.getBagOrder();
			param[1] = bag.getBagId();
			params.add(param);
		}
		
		//3.执行批量更新
		this.doBatchWork(sql, params);
	}

	@Override
	public List getBagBySurveId(Integer surveyId) {
		String hql = "From Bag b where b.survey.surveyId=?";
		List list = this.getSession().createQuery(hql).setInteger(0, surveyId).list();

		return list;
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		String hql = "From Bag b left join fetch b.questionSet where b.survey.surveyId = ? order by b.bagOrder asc";
		Bag bag = (Bag) this.getSession().createQuery(hql).setInteger(0, surveyId).setMaxResults(1).uniqueResult();
		return bag;
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		String hql = "From Bag b left join fetch b.questionSet "
				+ "where b.bagOrder < (select innerBag.bagOrder From Bag innerBag"
				+ " where innerBag.bagId=?) and b.survey.surveyId=? order by b.bagOrder desc";
		Bag bag = (Bag)this.getSession().createQuery(hql).setInteger(0, bagId).setInteger(1,surveyId).setMaxResults(1).uniqueResult();
		return bag;
	}

	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		String hql = "From Bag b left join fetch b.questionSet "
				+ "where b.bagOrder > (select innerBag.bagOrder From Bag innerBag"
				+ " where innerBag.bagId=?) and b.survey.surveyId=? order by b.bagOrder asc";
		Bag bag = (Bag)this.getSession().createQuery(hql).setInteger(0, bagId).setInteger(1,surveyId).setMaxResults(1).uniqueResult();
		return bag;
	}

	
}
