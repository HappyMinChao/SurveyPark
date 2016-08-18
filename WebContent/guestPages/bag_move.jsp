<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
</head>
<body>
	
	<s:include value="/resources_jsp/top.jsp"/>
	<%-- <s:property/> --%>
	<!-- 当前栈顶对象：Survey对象 -->
	<div class="block-div navigatorDiv">
		<div class="locationDiv">
			设计调查
		</div>
		<div class="backToIndexDiv">
			<s:a action="SurveyAction_myUncompletedSurvey.action?currentPageNo=1" namespace="/Guest">返回我的调查列表</s:a>
		</div>
	</div>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv">
			<img alt="Logo" src="<s:url value="%{logoPath}"/>">
			<s:property value="title"/>
		</div>
		<div class="backToIndexDiv">
			<s:a action="ToPageAction_bag_add?surveyId=%{surveyId}" namespace="/Guest">添加包裹</s:a>&nbsp;&nbsp;
			<s:a action="SurveyAction_adjustBagOrder?surveyId=%{surveyId}" namespace="/Guest">调整包裹顺序</s:a>
		</div>
	</div>
	<div class="block-div">
		<!-- 此时的栈顶元素为survey -->
		<table class="dashedTable">
			<s:if test="bagSet == null || bagSet.length ==0">
				<td>还没有包裹</td>
			</s:if>
			<s:else>
				<s:iterator value="bagSet">
						
						<tr>
							<td><s:property value="bagName"/></td>
							<td><s:a action="QuestionAction_toChoosenType?bagId=%{bagId}&surveyId=%{surveyId}" namespace="/Guest">添加问题</s:a></td>
							<td><s:a action="BagAction_edit?bagId=%{bagId}&surveyId=%{surveyId}" namespace="/Guest">编辑包裹</s:a></td>
							<td><s:a action="BagAction_remove?bagId=%{bagId}&surveyId=%{surveyId}" namespace="/Guest">删除包裹</s:a></td>
							<td><s:a action="BagAction_move?bagId=%{bagId}&surveyId=%{surveyId}" namespace="/Guest">移动/复制</s:a></td>
						</tr>
						<!-- 在遍历中当前栈顶元素为bag，所以可以直接使用questionSet -->
						<tr>
						<td>&nbsp;</td>
						<td>
							<%-- <s:property value="questionSet"/> --%>
							<s:include value="/guestPages/question_detail.jsp"/>
						</td>
					</tr>
					</s:iterator>
			</s:else>
			<!-- 在interator之外，故栈顶元素为survey -->
			<tr>
				<td colspan="2"> 
					<s:a action="SurveyAction_completed?surveyId=%{surveyId}" namespace="/Guest">完成调查</s:a>
				</td>
			</tr>
		</table>
	</div>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>