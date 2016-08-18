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
		<s:form action="UserAction_pay" namespace="/Guest">
			<%-- 直接注入到栈顶的User对象中 --%>
			<%-- <s:hidden name="userId" value="%{#session.loginUser.userId}"/> --%>
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">用户充值</td>
				</tr>
				<s:if test="hasActionErrors()">
					<tr>
						<td colspan="2" align="center">
							<s:actionerror/>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>充值金额</td>
					<td>
						<%-- 在User中没有的属性，需要在UserAction中另外设置一个属性来接收 --%>
						<s:textfield name="payAmount" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="充值"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>