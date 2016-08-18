package com.atguigu.survey.guest.component.service.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.base.m.BaseServcieImpl;
import com.atguigu.survey.guest.component.dao.i.AnswerDao;
import com.atguigu.survey.guest.component.dao.i.SurveyDao;
import com.atguigu.survey.guest.component.service.i.AnswerService;
import com.atguigu.survey.guest.entity.Answer;
import com.atguigu.survey.utils.DataProcessUtils;

@Service
public class AnswerServiceImpl extends BaseServcieImpl<Answer> implements
		AnswerService {

	@Autowired
	AnswerDao answerDao;
	@Autowired
	SurveyDao surveyDao;

	/**
	 * 
	 * radio单选题: question14:1 question14Other: //其中的14的other选项另外提交
	 * 
	 * checkebox多选题: question17:1 question17:2 question17Other:
	 * 
	 * select下拉列表: question18:1 question19:3
	 * 
	 * 简答题: question20:嘻嘻嘻嘻嘻嘻
	 * 
	 * 矩阵式单选: question22_0:0_0 question22_1:1_0 question22_2:2_0
	 * question22_3:3_0
	 * 
	 * 矩阵式多选： question23:0_0 question23:0_1 question23:1_0 question23:1_1
	 * question23:2_0 question23:2_1 question23:3_0 question23:3_1
	 * 
	 * 矩阵式下拉： question24:0_0_0 question24:0_1_0 question24:0_2_0
	 * question24:0_3_0 question24:1_0_0 question24:1_1_0 question24:1_2_0
	 * question24:1_3_0 question24:2_0_0 question24:2_1_0 question24:2_2_0
	 * question24:2_3_0 question24:3_0_0 question24:3_1_0 question24:3_2_0
	 * question24:3_3_0
	 * 
	 */

	@Override
	public void saveAnswer(Map<Integer, Map<String, String[]>> allBagAnwerMap,
			Integer surveyId) {

		//为了进行批处理
		List<Answer> answerList = new ArrayList<>();
		
		//提供保存Answer对象时需要的基本信息
		Date answerTime = new Date();
		String uuid = UUID.randomUUID().toString();
		
		/**
		 * 	将答案数据从allBagMap中取出，此时不关心某个答案属于哪个包裹，所以取allBagMap的values
		 * 	values: 一个个包裹答案的集合
		 */
		Collection<Map<String, String[]>> values = allBagAnwerMap.values();
		
		//声明一个缓存矩阵单选的答案数据的Map,  key: questionId  Value: 矩阵单选的答案，用于拼接
		Map<Integer, String> matrixRadioMap = new HashMap<>();
		
		//遍历values
		for (Map<String, String[]> paramMap : values) {
			
			//一个包裹的答案
			Set<Entry<String, String[]>> entrySet = paramMap.entrySet();
			//param为一个问题答案
			for (Entry<String, String[]> param : entrySet) {
				
				//每得到一个param都对应一个Answer对象
				Answer answer = new Answer(null, surveyId, null, answerTime, uuid, null, null);
				
				//得到每一个请求参数
				String paramName = param.getKey();
				String[] paramValArr = param.getValue();
				
				//要解析的答案请求参数的name值都是以question开头的，如果不是则不解析
				if(!paramName.startsWith("question")) continue ;
			
				//区分paramName的三种情况
				if(paramName.contains("Other")) {
					//文本框形式的其他项
					//questionId
					//paramName:question27Other
					String questionIdStr = paramName.substring(8, paramName.lastIndexOf("Other"));
					Integer questionId = Integer.parseInt(questionIdStr);
					answer.setQuestionId(questionId);
					
					//otherAnswers
					String otherAnswers = paramValArr[0];
					answer.setOtherAnswer(otherAnswers);
					
					answerList.add(answer);
					
				} else if(paramName.contains("_")) {
					//矩阵单选
					//检测当前paramName中的questionId
					String questionIdStr = paramName.substring(8, paramName.lastIndexOf("_"));
					Integer questionId = Integer.parseInt(questionIdStr);
					
					//根据questionId从matrixRadioMap中获取之前保存的旧的单选值
					String oldValue = matrixRadioMap.get(questionId);
					
					if(oldValue == null) {
						//如果旧值是空的，说明是第一次保存，保存当前值即可
						oldValue = paramValArr[0];
						
					}else{
						//如果旧值不为空，那么就将新的值追加到旧值后面，然后再保存
						oldValue = oldValue + "," + paramValArr[0];
						
					}
					
					//会将原来的值覆盖
					matrixRadioMap.put(questionId, oldValue);
					
					
				} else {
					//其他所有情况
					//解析得到questionId
					//paramName:question+questionId
					String questionIdStr = paramName.substring(8);
					Integer questionId = Integer.parseInt(questionIdStr);
					answer.setQuestionId(questionId);
					
					//mainAnswers
					String mainAnswers = DataProcessUtils.convertArrToStr(paramValArr);
					answer.setMainAnswer(mainAnswers);
					
					answerList.add(answer);
				}
			}
			
		}
		
		Set<Entry<Integer, String>> entrySet = matrixRadioMap.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			Integer questionId = entry.getKey();
			String mainAnswers = entry.getValue();
			//我靠， 我是靠的老师的代码吗？ ，怎么错了？
		  //Answer answer = new Answer(null, questionId, surveyId ,answerTime, uuid, mainAnswers, null);
			Answer answer = new Answer(null, surveyId, questionId ,answerTime, uuid, mainAnswers, null);
			answerList.add(answer);
		}
		
		/*for (Answer answer : answerList) {
			System.out.println(answer);
		}*/
		
		answerDao.batchSaveAnswerList(answerList);
		
		
		/*// 准备创建Answer 所需要的参数
		Date nowTime = new Date();
		String uuid = UUID.randomUUID().toString();
		// 一个调查答案map

		// 获取到survey中所有的包裹，根据包裹id获取到每个包裹中的 questions 答案
		Survey survey = surveyDao.getEntityById(surveyId);
		Set<Bag> bagSet = survey.getBagSet();
		Iterator iteratorBag = bagSet.iterator();
		while (iteratorBag.hasNext()) {
			// 通过迭代获取到一个包裹
			Bag bag = (Bag) iteratorBag.next();
			Integer bagId = bag.getBagId();
			// 获取到一个包裹的答案map
			Map<String, String[]> paramsBag = allBagAnwerMap.get(bagId); // 一个包裹中的参数

			Set<Entry<String, String[]>> paramsBagSet = paramsBag.entrySet();
			Iterator<Entry<String, String[]>> iteratorEntry = paramsBagSet
					.iterator();
			while (iteratorEntry.hasNext()) {
				*//**
				 * 一个调查中的答案：
				 * 
				 * bagId : 11 单选题： * question14 : 1 question14Other : xxxxx 单选题：
				 * question15 : 2 submit_next : 下一个包裹 surveyId : 19 bagId : 12
				 * 多选题数组 question17 : 1 question17Other : xxx 多选题数组： question16
				 * : 1 2 3 submit_next : 下一个包裹 surveyId : 19 bagId : 13
				 * submit_done : 完成调查 下拉列表 surveyId : 19 question18 : 2
				 * question19 : 2
				 *//*

				// 获取到包裹中，一个问题的答案
				Entry<String, String[]> paramQuestion = (Entry<String, String[]>) iteratorEntry
						.next();
				String paramsName = paramQuestion.getKey();
				// 通过前缀question过滤掉非答案参数
				if (paramsName.startsWith("question")) {
					//
					*//**
					 * 判断name的三种情况：
					 * 
					 * 1.quesiton+questionId 单选、多选、下拉、简答题、矩阵多选、矩阵下拉
					 * 2.question+questionId+Other 文本框形式其它项
					 * 3.question+questionId_rowId 矩阵单选
					 *//*
					// 带Other
					int questionId = 0;
					String otherAnswer = null;
					StringBuilder mainAnswer = new StringBuilder("");
					if (paramsName.endsWith("Other")) {
						// 说明为 文本框的其他项
						String questionIdStr = paramsName.substring(8, paramsName.lastIndexOf("Other"));
						questionId = Integer.parseInt(questionIdStr);
						otherAnswer = paramQuestion.getValue()[0];

						// 取出同一问题中非Other的答案
						String mainQuestionId = "question" + questionIdStr;
						String[] strings = paramsBag.get(mainQuestionId); // 单选、多选

						if (!ValidateUtils.validateCollection(strings) && otherAnswer == null){
							//两个都为空则不保存
							continue;
						}
						if(ValidateUtils.validateCollection(strings)){
							// 如果数组不为空，说明存在，删除
							paramsBag.remove(mainQuestionId);
							// 遍历数组，
							for (int i = 0; i < strings.length; i++) {
								if (i == 0) {
									mainAnswer.append(strings[i]);
								} else {
									mainAnswer.append(",").append(strings[i]);
								}
							}// 到此mainAnswer拼接完毕,可以创建答案并保持

						}// if

					}// 2.包含_ question+questionId_rowId 矩阵单选
					 //<input type='radio' name='question22_0' value='0_3' />
					else if (paramsName.contains("_")) {
						String questionIdStr = paramsName.substring(8,
								paramsName.lastIndexOf("_"));
						// 说明为 文本框的其他项  同一个问题的name不相同
						//获取到该包裹中所有的参数名
						
						questionId = Integer.parseInt(questionIdStr);
						
						// 取出矩阵单选的答案
						String[] paramsValue = paramQuestion.getValue();

						if (ValidateUtils.validateCollection(paramsValue)) {

							// 遍历数组，
							for (int j = 0 ; j < paramsValue.length; j++) {
								if (j == 0) {
									mainAnswer.append(paramsValue[j]);
								} else {
									mainAnswer.append(",").append(paramsValue[j]);
								}
							}// 到此matrixMainAnswer拼接完毕,可以创建答案并保持

						}//if
						
					} else {
						// quesiton+questionId 单选、多选、下拉、简答题、矩阵多选、矩阵下拉
						// 1.判断是否有Other,有对应的other不进行处理， 跳过
						String paramsNameOther = paramsName + "Other";
						// 2.根据paramsNameOther在paramsBag查找是否存在
						String[] otherParams = paramsBag.get(paramsNameOther);
						// 3.非空跳过跳过
						if (ValidateUtils.validateCollection(otherParams))
							continue;

						String questionIdStr = paramsName.substring(8);
						questionId = Integer.parseInt(questionIdStr);

						String[] paramsValue = paramQuestion.getValue();

						// 遍历数组，
						for (int i = 0; i < paramsValue.length; i++) {
							if (i == 0) {
								mainAnswer.append(paramsValue[i]);
							} else {
								mainAnswer.append(",").append(paramsValue[i]);
							}
						}// 到此matrixMainAnswer拼接完毕,可以创建答案并保持
					}//else

					Answer matrixAnswer = new Answer(null, surveyId,
							questionId, nowTime, uuid, mainAnswer.toString(),
							otherAnswer);
					answerDao.saveEntity(matrixAnswer);

				}//if
			}
			
		}
*/
		
		
		
	}
}
