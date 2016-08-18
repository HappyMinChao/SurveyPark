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
	<div>
		<s:form action="UserAction_login" namespace="/Guest">
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">用户登录</td>
				</tr>
				<s:if test="hasActionErrors()">
					<tr>
						<td colspan="2" align="center">
							<s:actionerror/>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>用户名</td>
					<td>
						<s:textfield name="userName" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;码</td>
					<td>
						<s:password name="userPwd" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="登录"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>