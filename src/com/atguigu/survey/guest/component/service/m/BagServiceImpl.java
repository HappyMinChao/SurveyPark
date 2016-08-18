package com.atguigu.survey.guest.component.service.m;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.BagDao;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.utils.ValidateUtils;

@Service
public class BagServiceImpl extends BaseServcieImpl<Bag> implements BagService {

	@Autowired
	private BagDao bagDao;

	@Override
	public void saveEntity(Bag bag) {
		bagDao.saveEntity(bag);
	}

	@Override
	public void removeBag(Bag bag) {
		bagDao.deleteEntity(bag);
	}

	@Override
	public Bag getEntityById(Integer bagId) {
		Bag bag = bagDao.getEntityById(bagId);
		return bag;
	}

	@Override
	public void updateEntity(Bag bag) {
		bagDao.updateEntity(bag);
	}

	@Override
	public void saveBagOrder(List<Bag> bagList) {
		bagDao.updateBagOrder(bagList);
		
	}

	@Override
	public Set getBagBySurveId(Integer surveyId) {
		List bagList = bagDao.getBagBySurveId(surveyId);
		Set bagSet = new HashSet(bagList);
		return bagSet;
	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		Bag bag = bagDao.getFirstBag(surveyId);
		return bag;
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		Bag prevBag = bagDao.getPrevBag(surveyId, bagId);
		return prevBag;
	}
	
	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		Bag bag = bagDao.getNextBag(surveyId, bagId);
		return bag;
	}
	
}
