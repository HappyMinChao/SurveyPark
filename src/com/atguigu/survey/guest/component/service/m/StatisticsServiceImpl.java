package com.atguigu.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.AnswerDao;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.service.i.StatisticsService;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;

@Service
public class StatisticsServiceImpl extends BaseServcieImpl<Survey> implements
		StatisticsService {

	@Autowired
	QuestionDao questionDao; 
	@Autowired
	AnswerDao answerDao;
	
	@Override
	public QuestionStatisticsModel getQsm(Integer questionId) {
		
		Question question = questionDao.getEntityById(questionId);
		
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		String lable = question.getQuestionName();
		int count = answerDao.getEngageCount(questionId);
		
		//项qsm中传递count
		qsm.setTotalCount(count);
		//向qsm中传递  lable 
		qsm.setQuestionName(lable);
		
		//获取List<OptionStatisticsModel> 对象
		List<OptionStatisticsModel> osmList = getOptionStatisticsModelList(question);
	
		qsm.setOsmList(osmList);
		
		return qsm;
	}

	private List<OptionStatisticsModel> getOptionStatisticsModelList(
			Question question) {

		List<OptionStatisticsModel> list = new ArrayList<OptionStatisticsModel>();
		//获取问题中的选项
		String[] options = question.getOptionsArray();
		
		for(int i = 0 ; i < options.length ; i++){
			//创建OptionStatisticsModel对象
			OptionStatisticsModel osm = new OptionStatisticsModel();
			
			//获取每个选项的lable
			osm.setLable(options[i]);
			//构建出option对应的value 或者是other是被单独存放的， 所以另行处理， 在此不用考虑
			String value = i+"";
			
			//查找i在答案中的次数           单选、 多选、 下拉菜单 都使用
			int optionCount = answerDao.getOptionCount(question.getQuestionId(),value);
			
			if(optionCount == 0) continue;
			
			osm.setCount(optionCount);
			
			list.add(osm);
		}
		
		if(question.isHasOther()){
			if(question.getOtherType() == 0){

				OptionStatisticsModel osm = new OptionStatisticsModel();
				String value = "other";
				int optionCount = answerDao.getOptionCount(question.getQuestionId(),value);
				
				osm.setLable("其他");
				osm.setCount(optionCount);
				
				list.add(osm);
			}
		}
		
		
		return list;
	}

	@Override
	public List getTextOtherList(Integer questionId) {
		
		List list = answerDao.getTextOtherList(questionId);
		
		return list;
	}

	@Override
	public List getMainTextList(Integer questionId) {
		List list = answerDao.getMainTextList(questionId);
		return list;
	}

	@Override
	public List<OptionStatisticsModel> getMatrixOsmList(Integer questionId,Integer rowNumber) {
		Question question = questionDao.getEntityById(questionId);
		
		String[] rowTitles = question.getMatrixRowTitlesArray();
		String[] colTitles = question.getMatrixColTitlesArray();
		
		List<OptionStatisticsModel> osmList = new ArrayList<OptionStatisticsModel>();
		
		for(int j = 0; j < colTitles.length; j++){
			
			OptionStatisticsModel osm = new OptionStatisticsModel();
			
			int osmCount = answerDao.getOptionCount(questionId, rowNumber+"_"+j);
			//如果该选项被选次数为0， 则放弃添加到osmList中
			if(osmCount == 0) continue;
			osm.setCount(osmCount);
			osm.setLable(rowTitles[rowNumber]+"_"+colTitles[j]);
			
			osmList.add(osm);
		}
		
		return osmList;
	}

	@Override
	public List<OptionStatisticsModel> getOptionMatrixOsmList(
			Integer questionId, Integer rowNumber, Integer colNumber) {
		Question question = questionDao.getEntityById(questionId);
		
		String[] rowTitles = question.getMatrixRowTitlesArray();
		String[] colTitles = question.getMatrixColTitlesArray();
		
		String[] optionsArray = question.getOptionsArray();
		
		List<OptionStatisticsModel> osmList = new ArrayList<OptionStatisticsModel>();
		
		for(int i = 0 ; i < optionsArray.length ; i ++){
			OptionStatisticsModel osm = new OptionStatisticsModel();
			String lable = optionsArray[i];
			int count = answerDao.getOptionCount(questionId, rowNumber+"_"+colNumber+"_"+i);
			if(count == 0)continue;
			
			osm.setLable(lable);
			osm.setCount(count);
			
			osmList.add(osm);
			
		}
		
		return osmList;
	}

	
	
}
