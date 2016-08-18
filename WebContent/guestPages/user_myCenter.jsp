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
	<div class="block-div navigatorDiv">
		<div class="locationDiv">我的基本信息</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
		<s:push value="#session.loginUser">
			<tr>
				<td class="leftTd">用户名</td>
				<td class="rightTd">
					<s:property value="userName"/>
				</td>
			</tr>
			<tr>
				<td class="leftTd">昵称</td>
				<td class="rightTd">
					<s:property value="nickName"/>
				</td>
			</tr>
			<tr>
				<td class="leftTd">我的余额</td>
				<td class="rightTd">
					￥<s:property value="balance"/>元
					<s:a action="ToPageAction_user_pay" namespace="/Guest">我要充值</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">用户状态</td>
				<td class="rightTd">
					<s:property value="payStatus?'VIP':'普通会员'"/>
					<s:if test="payStatus">
						您的会员到<s:property value="endDate"/>到期
					</s:if>
					<s:a action="ToPageAction_user_vip" namespace="/Guest">我要续费</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">Email</td>
				<td class="rightTd"><s:property value="email"/></td>
			</tr>
		</s:push>
		</table>
	</div>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv">我的调查</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable">
			<tr>
				<td class="leftTd">我发起的调查</td>
				<td class="rightTd">
					<!-- <a href="/SurveyParkExample/Guest/SurveyAction_myCompletedSurveyList.action">查看详情</a> -->
					<s:a action="SurveyAction_myCompletedSurvey?currentPageNo=1" namespace="/Guest">查看详情</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">我参与的调查</td>
				<td class="rightTd">
					<s:a action="SurveyAction_myEngagedSurvey" namespace="/Guest">查看详情</s:a>
				</td>
			</tr>
			<tr>
				<td class="leftTd">未完成的调查</td>
				<td class="rightTd">
					<s:a action="SurveyAction_myUncompletedSurvey?currentPageNo=1" namespace="/Guest">查看详情</s:a>
				</td>
			</tr>
		</table>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>