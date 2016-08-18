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
	
	<div class="locationDiv">我发起的调查</div>
	
	
	
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="#request.page.list == null || #request.page.list.length == 0 ">
				<tr>没有未完成的调查</tr>
			</s:if>
			<s:else>
			
				<tr>
					<td>Logo</td>
					<td>调查标题</td>
					<td>调查状态</td>
					<td>删除</td>
					<td>编辑</td>
				</tr>
				
				<%-- <s:property value="#request.page.list"/> --%>
				
				<s:iterator value="#request.page.list">
					
					<tr>
						<td> <img alt="Logo" src="<s:url value="%{logoPath}"></s:url>"> </td>
						<td> <s:property value="title"/> </td>
						<s:if test="completed">
						<td>已完成</td>
						</s:if>
						<s:else>
						<td><s:a action="SurveyAction_design?surveyId=%{surveyId}" namespace="/Guest">继续完善</s:a></td>
						</s:else>
						<td><s:a action="SurveyAction_remove?surveyId=%{surveyId}" namespace="/Guest">删除</s:a></td>
						<td><s:a href="SurveyAction_editSurvey?surveyId=%{surveyId}">编辑调查信息</s:a></td>
					</tr>
				</s:iterator>
			</s:else>
		</table>
		
		
	</div>
	
	<s:set value="'SurveyAction_myUncompletedSurvey'" var="pageActionName"></s:set>
	<s:set value="'/Guest'" var="pageNamespace"></s:set>
	
	<s:include value="/resources_jsp/pageNavigator.jsp"></s:include>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>