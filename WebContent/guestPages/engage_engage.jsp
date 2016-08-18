<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.atguigu.com/survey" prefix="atguigu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>
</head>
<body>
	
	<s:include value="/resources_jsp/top.jsp"/>
	<!-- 当前栈顶对象：Survey对象 -->
	<s:push value="#session.currentSurvey">
		<div class="block-div navigatorDiv">
			<div class="locationDiv">
				参与调查:
			</div>
			<div class="backToIndexDiv">
				<s:a action="SurveyAction_toIndexAction" namespace="/Guest">返回首页</s:a>
			</div>
		</div>
		
		<div class="block-div navigatorDiv">
			<div class="locationDiv">
				<img alt="Logo" src="<s:url value="%{logoPath}"/>">
				<span style="color:red;"><s:property value="title"/></span>
			</div>
		</div>
		<div class="block-div">
			<!-- 此时的栈顶元素为survey -->
			<table class="dashedTable">
				<s:if test="#request.currentBag==null">
					<tr><td>还没有包裹</td></tr>
				</s:if>
				<s:else>
					<s:form action="EngageAction_engage?surveyId=%{surveyId}" namespace="">
						<s:hidden name="surveyId" value="%{surveyId}"></s:hidden>
				 	  <s:push value="#request.currentBag">
						<s:hidden name="bagId" value="%{bagId}"></s:hidden>
						<!-- 此处不应该为遍历bagSet，应为从域中取出的bag对象 -->
						<tr>
							<td><h3>问题标题：<span style="color:red;"><s:property value="bagName"/></span></h3></td>
						</tr>
						<!-- 在遍历中当前栈顶元素为bag，所以可以直接使用questionSet -->
						<tr>
							<td>
								<%-- <s:property value="questionSet"/> --%>
								<s:include value="/guestPages/engage_question_detail.jsp"/>
							</td>
						</tr>
					
						<tr>
							<td colspan="2"> 
								<div style="text-align:center;">
								bagOrder:<s:property value="bagOrder"/>
								minBagOrder:<s:property value="minBagOrder"/>
								<s:if test="bagOrder > minBagOrder">
									<s:submit name="submit_prev" value="上一个包裹"></s:submit>
								</s:if>
								<s:if test="bagOrder < maxBagOrder">
									<s:submit name="submit_next" value="下一个包裹"></s:submit>
								</s:if>
								<s:if test="bagOrder == maxBagOrder">
									<s:submit name="submit_done" value="完成调查"></s:submit>
								</s:if>
								<s:submit name="submit_quit" value="放弃调查"></s:submit>
								</div>
							</td>
						</tr>
				   	  </s:push>
					</s:form>
				</s:else>	
			</table>
		</div>
	</s:push>
	<s:debug></s:debug>
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>