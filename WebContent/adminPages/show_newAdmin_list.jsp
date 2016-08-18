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

	<s:include value="/resources_jsp/admin_top.jsp"/>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv ">所有的资源列表</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable" style="text-align: center;">
			<s:if test="#request.newAdminList == null || #request.newAdminList.empty">
				<tr>
					<td>生成账号失败！</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>管理员账号</td><td>管理员密码</td>
				</tr>
				<s:iterator value="#request.newAdminList">
					<tr>
						<td>
							<s:property value="adminName"/>
						</td>
						<td>
							<s:property value="adminPwd"/>
						</td>
					</tr>	
				</s:iterator>
			</s:else>
		</table>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>