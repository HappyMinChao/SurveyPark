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
		<s:form namespace="/Guest" action="UserAction_vip">
			<%-- <s:hidden name="userId" value="%{#session.loginUser.userId}"/> --%>
			<table class="formTable">
				<tr>
					<td colspan="2" align="center">会员续费</td>
				</tr>
				<tr>
					<td>请选择会员期限</td>
					<td>
						<input id="vipDays_radio01" type="radio" name="vipDays" value="30"/>
						<label for="vipDays_radio01">30天 300元 [10元/天]</label>
						<br/>
						
						<input id="vipDays_radio02" type="radio" name="vipDays" value="90"/>
						<label for="vipDays_radio02">90天 720元 [8元/天]</label>
						<br/>
						
						<input id="vipDays_radio03" type="radio" name="vipDays" value="180" checked="checked"/>
						<label for="vipDays_radio03"><span id="hotWords">180天 900元 [5元/天]</span></label>
						<br/>
						
						<input id="vipDays_radio04" type="radio" name="vipDays" value="360"/>
						<label for="vipDays_radio04">360天 1080元[3元/天]</label>
						<br/>
						
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="开通"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>

	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>