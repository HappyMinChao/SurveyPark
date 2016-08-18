package com.atguigu.survey.guest.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.atguigu.survey.guest.entity.Question;
import com.atguigu.survey.utils.GlobalNames;
import com.atguigu.survey.utils.HTMLTagUtils;
import com.atguigu.survey.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/*
 * 创建自定义标签的步骤
	[1]创建自定义标签的处理器类，需要继承SimpleTagSupport
	[2]在xxx.tld文件中注册这个类，声明标签和标签的属性
	[3]在JSP页面上通过taglib指令引入后使用
 */

public class QuestionTag extends SimpleTagSupport {

	private String currentBagIdOGNL;

	/*
	 * 在使用自定义标签时， 主要调用这个方法
	 */

	@Override
	public void doTag() throws JspException, IOException {
		// 为拼接字符串准备StringBuilder
		StringBuilder finalString = new StringBuilder("");
		// pageContext可以获取到jsp隐含对象中的其他八个对象
		PageContext pageContext = (PageContext) this.getJspContext();

		// 要回显数据，必须获取到session中的allBagAnswerMap
		HttpSession session = pageContext.getSession();
		Map<Integer, Map<String, String[]>> allBagAnswerMap = (Map<Integer, Map<String, String[]>>) session
				.getAttribute(GlobalNames.ALL_BAG_ANSWER_MAP);

		/**
		 * 根据bagId属性在值栈中获取到bagId对应的值 标签的使用方法 <atguigu:generateQuestion
		 * currentBagIdOGNL="bagId"/>
		 */

		// 获取当前包裹的id, 根据当前包裹的id在allBagAnswerMap中查找到相应的paramterMap
		Integer currentBagId = parseCurrentBagId(currentBagIdOGNL);
		Map<String, String[]> parameters = allBagAnswerMap.get(currentBagId);

		// 获取遍历过程中栈顶对象Question
		Question question = getQuestion();
		// 获取当前question对象的类型
		int qType = question.getQuestionType();
		// 获取当前questionId
		Integer questionId = question.getQuestionId();
		// 获取是否有其他项
		boolean hasOther = question.isHasOther();

		// 根据questionType判断是 raido、checkebox、selecte……
		if (qType == 0) {
			// 单选框

			// 获取当前question对象中的选项数组
			String[] options = question.getOptionsArray();

			for (int i = 0; i < options.length; i++) {
				// 准备生成radio的参数idStr, name, value, option, checkedStr, isBr
				String name = "question" + questionId;
				String idStr = "radio" + questionId + i;
				String value = "" + i;
				String option = options[i];
				boolean isBr = question.isBr();

				// 根据name 获取到 parameters 中value 和 当前要显示标签的 value 判断是否
				// 添加checked属性
				boolean isChecked = checkedRedisplay(parameters, name, value);
				String checkedStr = isChecked ? " checked = 'checked' " : " ";

				// 向页面中显示标签
				String radio = HTMLTagUtils.generateRadio(idStr, name, value,
						option, checkedStr, isBr);
				finalString.append(radio);
			}
			if (hasOther) {
				/*
				 * 0: 和主题性一致 1: 文本框
				 */

				int otherType = question.getOtherType();

				if (otherType == 0) {
					String name = "question" + questionId;
					String idStr = "radio" + questionId;
					boolean isBr = question.isBr();
					String value = "other";
					// 根据name 获取到 parameters 中value 和 当前要显示标签的 value 判断是否
					// 添加checked属性
					boolean isChecked = checkedRedisplay(parameters, name,
							value);
					String checkedStr = isChecked ? " checked = 'checked' "
							: " ";

					// 向页面中显示标签
					String radio = HTMLTagUtils.generateRadio(idStr, name,
							value, "其他", checkedStr, isBr);
					finalString.append(radio);
				}
				if (otherType == 1) {
					String name = "question" + questionId + "Other";

					String value = redisplayText(name, parameters);

					// 向页面中显示标签
					String radio = HTMLTagUtils.generateText(name, value, "其他");
					finalString.append(radio);
				}

			}

		}
		if (qType == 1) {
			// 多选框

			// 获取当前question对象中的选项数组
			String[] options = question.getOptionsArray();

			for (int i = 0; i < options.length; i++) {
				// 准备生成radio的参数idStr, name, value, option, checkedStr, isBr
				String idStr = "radio" + questionId + i;
				String name = "question" + questionId;
				String value = "" + i;
				String option = options[i];
				boolean isBr = question.isBr();

				// 根据name 获取到 parameters 中value 和 当前要显示标签的 value 判断是否
				// 添加checked属性
				boolean isChecked = checkedRedisplay(parameters, name, value);
				String checkedStr = isChecked ? " checked = 'checked' " : " ";

				// 向页面中显示标签
				String radio = HTMLTagUtils.generateCheckeBox(idStr, name,
						value, option, checkedStr, isBr);
				finalString.append(radio);
			}

			if (hasOther) {
				/*
				 * 0: 和主题性一致 1: 文本框
				 */

				int otherType = question.getOtherType();

				if (otherType == 0) {
					String name = "question" + questionId;
					String idStr = "radio" + questionId;
					boolean isBr = question.isBr();
					String value = "other";
					// 根据name 获取到 parameters 中value 和 当前要显示标签的 value 判断是否
					// 添加checked属性
					boolean isChecked = checkedRedisplay(parameters, name,
							value);
					String checkedStr = isChecked ? " checked = 'checked' "
							: " ";

					// 向页面中显示标签
					String radio = HTMLTagUtils.generateCheckeBox(idStr, name,
							value, "其他", checkedStr, isBr);
					finalString.append(radio);
				}
				if (otherType == 1) {
					String name = "question" + questionId + "Other";

					String value = redisplayText(name, parameters);

					// 向页面中显示标签
					String radio = HTMLTagUtils.generateText(name, value, "其他");
					finalString.append(radio);
				}

			}

		}
		if (qType == 2) {
			// 下拉列表

			// 获取当前question对象中的选项数组
			String[] options = question.getOptionsArray();
			StringBuilder allOptions = new StringBuilder("");

			String name = "question" + questionId;
			for (int i = 0; i < options.length; i++) {
				// 准备生成radio的参数idStr, name, value, option, checkedStr, isBr
				String idStr = "radio" + questionId + i;
				String value = "" + i;
				String option = options[i];

				// 根据name 获取到 parameters 中value 和 当前要显示标签的 value 判断是否
				// 添加checked属性
				boolean isChecked = checkedRedisplay(parameters, name, value);
				String selectedStr = isChecked ? " selected = 'selected' "
						: " ";

				String optionStr = HTMLTagUtils.generateOption(value, option,
						selectedStr);
				// 向页面中显示标签
				allOptions.append(optionStr);
			}

			String select = HTMLTagUtils.generateSelect(name,
					allOptions.toString());
			finalString.append(select);
		}
		if (qType == 3) {
			// 简答题
			String name = "question" + questionId;
			String value = redisplayText(name, parameters);

			String text = HTMLTagUtils.generateText(name, value, "");
			finalString.append(text);
		}
		
		
		
		if (qType == 4 || qType == 5 || qType == 6) {
			
			StringBuilder allTrBuilder = new StringBuilder("");
			StringBuilder tdBuilder = new StringBuilder("");
			//一.第一行的处理
			// 1.空单元格
			String emptyTd = HTMLTagUtils.generateTd("&nbsp;");
			tdBuilder.append(emptyTd);
			
			//2.获取列标题数组
			String[] colTitlesArray = question.getMatrixColTitlesArray();
			//3.遍历列标题数组，添加到td
			for(int i = 0; i < colTitlesArray.length; i++){
				// 1.获取td单元格内容
				String cellContent = colTitlesArray[i];
				// 2.创建单元格
				String contentTd = HTMLTagUtils.generateTd(cellContent);
				
				tdBuilder.append(contentTd);
			}
			
			// 创建第一行tr
			String firstTr = HTMLTagUtils.generateTr(tdBuilder.toString());
			//把第一行的html代码添加到所有行中
			allTrBuilder.append(firstTr);
			// 每次把一行的html代码添加到总行中时，对tdBuilder清空，以备其他再用
			tdBuilder.delete(0, tdBuilder.length());
			
			
			//二、其他行的处理。
			//1.获取到行标题数组
			String[] rowTitlesArray = question.getMatrixRowTitlesArray();
			for (int i = 0; i < rowTitlesArray.length; i++) {
				//1.创建第一类的td
				String firstCloTd = HTMLTagUtils.generateTd(rowTitlesArray[i]);
				tdBuilder.append(firstCloTd);
				//2.创建其他类td  需根据qType进行判断是 radio  checkbox select
				// 创建列标题td
				for (int j = 0; j < colTitlesArray.length; j++) {
					// 3.根据不同的题型，创建不同的td内容， 由于在value 或name中要用到ijk
					String cellContent = null;
					if (qType == 4) {
						// 矩阵式单选框
						String idStr = "question"+questionId+i+j;
						String name = "question"+questionId+"_"+i;
						String value = i+"_"+j;
						String option = "点我更容易";
						String checkedStr = checkedRedisplay(parameters, name, value)?"checked = 'checked'":"";
						boolean isBr = question.isBr();
						
						cellContent = HTMLTagUtils.generateRadio(idStr, name, value, option, checkedStr, isBr);
					}
					if (qType == 5) {
						// 矩阵式多选框
						String idStr = "question"+questionId+i+j;
						String name = "question"+questionId;
						String value = i+"_"+j;
						String option = "点我更容易";
						String checkedStr = checkedRedisplay(parameters, name, value)?"checked = 'checked'":"";
						boolean isBr = question.isBr();
						
						cellContent = HTMLTagUtils.generateCheckeBox(idStr, name, value, option, checkedStr, isBr);
					}
					if (qType == 6) {
						// 矩阵式下拉列表
						String[] options = question.getOptionsArray();
						StringBuilder optionBulider = new StringBuilder("");
						
						String name = "question"+questionId;
						
						//创建下拉列表中的option
						for(int k = 0; k<options.length; k++){

							String value = i+"_"+j+"_"+k;
							String option = options[k];
							String selected = checkedRedisplay(parameters, name, value)?"selected = 'selected'":"";
							
							String generateOption = HTMLTagUtils.generateOption(value, option, selected);
							optionBulider.append(generateOption);
						}
						
						//根据option创建select,作为cellContents
						cellContent = HTMLTagUtils.generateSelect(name, optionBulider.toString());
	
					}
					
					//创建td
					String generateTd = HTMLTagUtils.generateTd(cellContent);
					tdBuilder.append(generateTd);
				}
				//讲tdBuilder添加到allRowBuilder中
				String generateTr = HTMLTagUtils.generateTr(tdBuilder.toString());
				allTrBuilder.append(generateTr);
				//把tdBuilder清空
				tdBuilder.delete(0, tdBuilder.length());

			}

			String finalTable = HTMLTagUtils.generateTable(allTrBuilder.toString());
			finalString.append(finalTable);
		}


		// 向页面中输出
		JspWriter out = pageContext.getOut();
		out.print(finalString.toString());

	}

	/**
	 * 1.根据标签的name属性在 parameters 中获取到 提交的value[]
	 * 2.根据当前标签的value在数组中查找，如果找到则返回true 表示需要添加 checked = 'checked'
	 * 3.如果找不到，返回false
	 */

	private boolean checkedRedisplay(Map<String, String[]> parameters,
			String name, String value) {
		if (!ValidateUtils.validateMap(parameters))
			return false;
		// 1.根据name获取parameters中提交的values
		String[] values = parameters.get(name);
		// 如果value为空 返回false
		if (!ValidateUtils.stringValidate(value))
			return false;
		// 如果values为空 返回false
		if (!ValidateUtils.arrayValidate(values))
			return false;
		for (int i = 0; i < values.length; i++) {
			if (value.equals(values[i]))
				return true;
		}
		return false;
	}

	private String redisplayText(String name, Map<String, String[]> parameters) {
		if (!ValidateUtils.validateMap(parameters))
			return "";
		return parameters.get(name)[0];
	}

	private Question getQuestion() {
		Question question = (Question) ActionContext.getContext()
				.getValueStack().getRoot().get(0);
		return question;
	}

	private Integer parseCurrentBagId(String currentBagIdOGNL) {
		ValueStack vs = ActionContext.getContext().getValueStack();
		Integer currentBagId = (Integer) vs.findValue(currentBagIdOGNL);

		return currentBagId;
	}

	public String getCurrentBagIdOGNL() {
		return currentBagIdOGNL;
	}

	public void setCurrentBagIdOGNL(String currentBagIdOGNL) {
		this.currentBagIdOGNL = currentBagIdOGNL;
	}

}
