package com.atguigu.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.AnswerDao;
import com.atguigu.survey.guest.component.dao.i.QuestionDao;
import com.atguigu.survey.guest.component.dao.i.SurveyDao;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Answer;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.ValidateUtils;

@Service
public class SurveyServiceImpl extends BaseServcieImpl<Survey> implements SurveyService{
	
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public void deleteSurvey(Survey survey) {
		
		surveyDao.deleteEntity(survey);
	}

	
	@Override
	public List getUncompletedListByUser(User user) {
		List completedList = surveyDao.getUncompletedListByUser(user);
		return completedList;
	}

	@Override
	public Page<Survey> getUncompletedPage(String currentPageNoStr, String pageSizeStr,User user){
		int totalRecord = surveyDao.getSingleValue(false, user);
		Page<Survey> page = new Page<Survey>(currentPageNoStr, pageSizeStr, totalRecord);
		List list = surveyDao.getCompletedListPage(false, page.getCurrentPageNo(), page.getPageSize(), user);
		page.setList(list);
		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String currentPageNoStr, String pageSizeStr,User user) {
		int totalRecord = surveyDao.getSingleValue(true, user);
		Page<Survey> page = new Page<Survey>(currentPageNoStr, pageSizeStr, totalRecord);
		List list = surveyDao.getCompletedListPage(true, page.getCurrentPageNo(), page.getPageSize(), user);
		page.setList(list);
		return page;
	}

	@Override
	public boolean completedSurvey(Integer surveyId) {
		//1.查询出需完成的survey
		Survey survey = surveyDao.getEntityById(surveyId);
		//2.判断查询出来的survey中的bagSet是否为空
		Set<Bag> bagSet = survey.getBagSet();
		//3.判断survey中的bagSet是否为空,保证调查下有包裹
		if(!ValidateUtils.validateCollection(bagSet))return false;
		//如果bagSet不为空， 则判断每个bag中的questionSet是否为空，保证包裹下有问题
		for (Bag bag : bagSet) {
			Set questionSet = bag.getQuestionSet();
			if(!ValidateUtils.validateCollection(questionSet)) return false;
		}
		
		//4.验证通过，完成调查
		survey.setCompleted(true);
		survey.setCompletedTime(new Date());
		return true;
	}


	@Override
	public List<Survey> getTopCompletedSurvey() {
		List<Survey> list = surveyDao.getTopCompletedSurvey();
		return list;
	}


	@Override
	public List<Survey> getTopHotSurvey() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page findAllAvailableSurvey(String pageStrNo, int pageSize) {
		Page page = surveyDao.getPageCompletedSurvey(pageStrNo, pageSize);
		return page;
	}


	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {
		surveyDao.saveEngageMsg(userId, surveyId) ;
	}


	@Override
	public Page<Survey> getMyEngageSurveyWithPage(Integer userId,String currentPageNo,String pageSize) {
		//1.通过userId获取到参与调查的surveyId数组
		Page<Survey> page = surveyDao.getMyEngageSurveyWithPage(userId,currentPageNo,pageSize);
		
		return page;
	}


	@Override
	public HSSFWorkbook generateWorkBook(Integer surveyId) {
		//一、获取survey下所有的Question
		Survey survey = surveyDao.getEntityById(surveyId);
		List<Question> questionList = questionDao.getQuestionBySurvey(survey);
		
		int totalCount = answerDao.getAnswerCount(surveyId);
		
		/*
		 * 把所有的答案整理放在Map<String , Map<Integer, String>>中
		 * 	String :  UUID
		 * 	Map<Integer, String>  每个答案
		 * 		Integer : questionId
		 * 		String : mainAnswer + OtherAnswer	
		 */
		Map<String , Map<Integer, String>> bagMap = new HashMap<String , Map<Integer, String>>();
		
		
		//1.首先根据surveyId获取到所有不重复的uuid
		List<String> uuidList = answerDao.getUUIDBySurveyId(surveyId);
		
		for (String uuid : uuidList) {
			//2.根据uuid,surveyId获取答案
			List<Answer> answers = answerDao.getAnswerByUUIDAndSurveyId(surveyId, uuid);
			Map<Integer, String>  smallMap = new HashMap<Integer, String>();
			for (Answer answer : answers) {
				//answers里面的答案都具有相同的uuid
				Integer questionId = answer.getQuestionId();
				String mainAnswer = answer.getMainAnswer();
				String otherAnswer = answer.getOtherAnswer();
				
				String content=null;
				String oldContent = smallMap.get(answer.getQuestionId());
				
				if(ValidateUtils.stringValidate(mainAnswer)||ValidateUtils.stringValidate(otherAnswer)){
					
					content = ValidateUtils.stringValidate(mainAnswer) ? mainAnswer : otherAnswer;
					
					
					if(null == oldContent){
						//第一次添加答案
						oldContent = content;
						
					}else{
						oldContent = oldContent+","+content;
					}
				}else{
					continue;
				}
				smallMap.put(questionId, oldContent);
			}
			bagMap.put(uuid, smallMap);
		}
		
		/*Set<Entry<String, Map<Integer, String>>> entrySet = bagMap.entrySet();
		
		for (Entry<String, Map<Integer, String>> entry : entrySet) {
			String uuid = entry.getKey();
			Map<Integer, String> smallMap = entry.getValue();
			Set<Entry<Integer, String>> answerEntry = smallMap.entrySet();
			for (Entry<Integer, String> entry2 : answerEntry) {
				System.out.println(uuid+" " +entry2.getKey() + " " + entry2.getValue() );
			}
			
		}
		System.out.println();*/
		
		//二、生成工作表
		//1.创建Excel文件对应的HSSFWorkBook对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//2.创建工作表对象：HSSFSheet
		HSSFSheet sheet = workbook.createSheet(survey.getTitle()+" "+totalCount+"人次参与");
		
		//3.创建行对象：HSSFRow
		//rownum是行的索引从0开始的
		HSSFRow row = sheet.createRow(0);
		
		//4.如果参与调查的总人数等于0
		if(totalCount == 0) {
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("当前调查还没有人参与！");
			return workbook;
		}
		
		//5.如果不为0，则在第一行显示问题标题
		for (int i = 0; i < questionList.size(); i++) {
			
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(questionName);
			
		}
		
		Set<Entry<String, Map<Integer, String>>> bigEntrySet = bagMap.entrySet();
		ArrayList<Entry<String, Map<Integer, String>>> bigEntryList = new ArrayList<>(bigEntrySet);
		
		for(int i = 0; i < bigEntryList.size(); i++) {
			
			Entry<String, Map<Integer, String>> entry = bigEntryList.get(i);
			String uuid = entry.getKey();
			Map<Integer, String> samllMap = entry.getValue();
			row = sheet.createRow(i + 1);
			
			for(int j = 0; j < questionList.size(); j++) {
				
				Question question = questionList.get(j);
				Integer questionId = question.getQuestionId();
				String answerContent = samllMap.get(questionId);
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(answerContent);
			}
			
		}

		for (int i = 0; i < questionList.size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
		return workbook;
	}

	/*
	 * 老师的从数据库中查出来的List代码放在MapMap<String, Map<Integer, String>>中
	 * 
	 * Map<String, Map<Integer, String>> bigMap = new HashMap<String, Map<Integer,String>>();
		
		for (Answer answer : answers) {
			
			String uuid = answer.getUuid();
			
			//key：questionId，value：答案内容
			Map<Integer, String> smallMap = bigMap.get(uuid);
			if(smallMap == null) {
				smallMap = new HashMap<>();
				bigMap.put(uuid, smallMap);
			}
			
			Integer questionId = answer.getQuestionId();
			
			String oldContent = smallMap.get(questionId);
			
			if(ValidateUtils.stringValidate(oldContent)) {
				oldContent = oldContent + ",";
			}else{
				oldContent = "";
			}
			
			String mainAnswers = answer.getMainAnswers();
			if(ValidateUtils.stringValidate(mainAnswers)) {
				oldContent = oldContent + mainAnswers;
			}
			
			String otherAnswers = answer.getOtherAnswers();
			if(ValidateUtils.stringValidate(otherAnswers)) {
				oldContent = oldContent + "[其他项：" + otherAnswers + "],";
			}
			
			oldContent = DataProcessUtils.removeLastComma(oldContent);
			
			smallMap.put(questionId, oldContent);
			
		}
	 * 
	 */
	
}
