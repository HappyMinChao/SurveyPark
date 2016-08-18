package com.atguigu.survey.guest.component.dao.i;

import java.util.List;
import java.util.Set;

import com.atguigu.survey.base.i.BaseDao;
import com.atguigu.survey.guest.entity.Bag;

public interface BagDao extends BaseDao<Bag> {
	public abstract void updateBagOrder(List<Bag> bagList);

	public abstract List getBagBySurveId(Integer surveyId);

	public abstract Bag getFirstBag(Integer surveyId);
	public abstract Bag getPrevBag(Integer surveyId,Integer bagId);
	public abstract Bag getNextBag(Integer surveyId,Integer bagId);
}
