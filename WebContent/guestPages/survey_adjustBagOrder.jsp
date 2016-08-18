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
	<div  class="block-div navigatorDiv">
	
		<table class="dashedTable">
		
			<s:if test="hasActionErrors()">
				<tr>
					<td></td>
					<td>
						<s:actionerror/>
					</td>
				</tr>
			</s:if>
		
			<s:form action="SurveyAction_saveBagOrder" namespace="/Guest" method="post">
				<!-- 栈顶元素为survey -->
				<s:hidden name="surveyId" value="%{surveyId}"></s:hidden>
				<s:iterator value="bagSet" status="myStatus">
					<!-- 栈顶元素为bag -->
					<tr>
						<td>
							<s:property value="bagName"/>
						</td>
						<td>
							<!-- 在提交表单时， 栈顶元素为survey，参数注入根据name属性 -->
							<s:hidden name="bagList[%{#myStatus.index}].bagId" value="%{bagId}"></s:hidden>
							<s:textfield name="bagList[%{#myStatus.index}].bagOrder" value="%{bagOrder}"></s:textfield>
						</td>
					</tr>
				</s:iterator>
				<tr><td></td><td></td></tr>
				<tr><td colspan="2">
					<div style="text-align:center;"><s:submit value="提交"></s:submit></div>
				</td></tr>
				
			</s:form>
		</table>
		
	</div>
		
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>