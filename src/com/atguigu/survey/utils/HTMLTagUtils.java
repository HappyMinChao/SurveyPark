package com.atguigu.survey.utils;

public class HTMLTagUtils {
	
	/**
	 * 	其他：<table>cellContent</table>
	 *     生成文本框
	 */
	public static String generateTable(String cellContent){
		return "<table>"+ cellContent +"</table>";
	}
	
	/**
	 * 	其他：<tr>cellContent<tr>
	 *     生成文本框
	 */
	public static String generateTr(String cellContent){
		return "<tr>"+ cellContent +"</tr>";
	}
	
	/**
	 * 	其他：<td>cellContent<td>
	 *     生成文本框
	 */
	public static String generateTd(String cellContent){
		return "<td>"+ cellContent +"</td>";
	}
	
	/**
	 * 	其他：<input name="dio" value="xxx" type="text"/>
	 *     生成文本框
	 */
	public static String generateText(String name, String value,String option){
		return "<label>"+ option +"</label><input name='"+ name +"' value='"+ value +"' type='text'/>";
	}

	/**
	 * 根据给定数据生成单选按钮HTML代码
	 * <input id="radio50" type="radio" name="question5" value="0"/><label for="radio50">选项01</label><br/>
	 *		idStr : lable for 属性使用
	 * 		name  : 标签的名字
	 * 		value : 提交表单使用
	 * 		option: lable中显示的数据
	 * 		checked: 是否默认选中，用于回显    
	 * 			checked = 'checked'  或   ""
	 * 		isBr  : 是否换行
	 * @return
	 */
	public static String generateRadio(String idStr, String name, String value,String option, String checkedStr , boolean isBr){
		return "<input id='"+ idStr +"' type='radio' name='"+ name +"' value='"+ value +"' "+checkedStr +"/><label for='" + idStr +"'>"+option+"</label>"+(isBr?"<br/>":"");
	}
	
	/**
	 * 根据给定数据生成多选按钮HTML代码
	 * <input id="radio50" type="radio" name="question5" value="0"/><label for="radio50">选项01</label><br/>
	 *		idStr : lable for 属性使用
	 * 		name  : 标签的名字
	 * 		value : 提交表单使用
	 * 		option: lable中显示的数据
	 * 		checked: 是否默认选中，用于回显    
	 * 			checked = 'checked'  或   ""
	 * 		isBr  : 是否换行
	 * @return
	 */
	public static String generateCheckeBox(String idStr, String name, String value,String option, String checkedStr , boolean isBr){
		return "<input id='"+ idStr +"' type='checkbox' name='"+ name +"' value='"+ value +"' "+checkedStr +"/><label for='" + idStr +"'>"+option+"</label>"+(isBr?"<br/>":"");
	}
	
	/**
	 * 根据给定数据生成option HTML代码
	 * 		<option value="xxx" selected="selected">xxx</option>
	 *		
	 * 		value : 提交表单使用
	 * 		option: 下拉列表中显示的数据
	 * 		selected: 是否默认选中，用于回显    
	 * 			selected = 'selected'  或   ""
	 * 		
	 * @return
	 */
	public static String generateOption(String value,String option, String selected){
		return "<option value='"+ value +"' "+selected +">"+option+"</option>";
	}
	
	/**
	 * 根据给定数据生成select HTML代码
	 * 	<select name="xxx>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
		</select>
	 *		
	 * 		value : 提交表单使用
	 * 		option: 下拉列表中显示的数据
	 * 		selected: 是否默认选中，用于回显    
	 * 			selected = 'selected'  或   ""
	 * 		
	 * @return
	 */
	public static String generateSelect(String name,String allOptions){
		return "<select name='"+ name +"'>" + allOptions +"</select>";
	}
	
}
