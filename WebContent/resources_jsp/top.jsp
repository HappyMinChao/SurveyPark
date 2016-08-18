<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="guestInfoBlock">
	<s:if test="#session.loginUser == null">
		<div id="guestInfoContent">
			<s:a value="/guestPages/user_login.jsp">登录</s:a>
			|
			<s:a value="/guestPages/user_regist.jsp">注册</s:a>&nbsp;
			
			<s:a value="/index.jsp">首页</s:a>
		</div>
	</s:if>
	<s:else>
		<div id="guestInfoContent">
			欢迎您<s:property value="#session.loginUser.nickName"/>,
			<s:a action="ToPageAction_user_myCenter" namespace="/Guest">个人中心</s:a>&nbsp;
			<s:a action="UserAction_logout" namespace="/Guest">退出登录</s:a>&nbsp;
			
			<s:if test="#session.loginUser.payStatus">
				<s:a action="ToPageAction_survey_create">创建一个新的调查</s:a>
			</s:if>
			
			<s:a value="/index.jsp">首页</s:a>
		</div>
	</s:else>
</div>
<div class="block-div" id="headTitle">
	<img src="<s:url value="/resources_static/surveyLogo.png"/>"/>
</div>