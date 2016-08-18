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
	
	<table class="invisibleTable">
		<tr>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最多人参与的调查Top10</span>
					<span class="rightSpan"><s:a action="SurveyAction_findAllAvailableSurvey" namespace="/Guest">查看全部可以参与的调查</s:a></span>
					<br/>
						<ul>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
							<li>假数据</li>
						</ul>
				</div>
			</td>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最新调查Top10</span>
					<span class="rightSpan"><s:a action="SurveyAction_findAllAvailableSurvey" namespace="/Guest">查看全部可以参与的调查</s:a></span>
					<br/>
					<!-- 从request域中获取到top10CompletedList判断是否为空 -->
					<s:if test="#request.top10CompletedList == null || #request.top10CompletedList.size() == 0">
						<tr><td>现在还没有新创建的调查</td></tr>
					</s:if>
					<s:else>
						<s:iterator value="#request.top10CompletedList">
					
							<!-- 栈顶元素为survey -->
							<ul>
								<li>
									<s:a action="EngageAction_entry?surveyId=%{surveyId}" namespace="/Guest"><s:property value="title"/></s:a>
									<h3></h3>
								</li>
							</ul>
							
						
						</s:iterator>
					</s:else>
					
				</div>
			</td>
		</tr>
	</table>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>