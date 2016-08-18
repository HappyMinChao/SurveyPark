package com.atguigu.survey.guest.component.service.i;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.atguigu.survey.guest.entity.Bag;

@Transactional
public interface BagService {

	public abstract void saveEntity(Bag bag);
	public abstract void removeBag(Bag bag);
	public abstract Bag getEntityById(Integer bagId);
	public abstract void updateEntity(Bag t);
	public abstract void saveBagOrder(List<Bag> bagList);
	public abstract Set getBagBySurveId(Integer surveyId);
	public abstract Bag getFirstBag(Integer surveyId);
	public abstract Bag getPrevBag(Integer surveyId, Integer bagId);
	public Bag getNextBag(Integer surveyId, Integer bagId) ;
	
}
