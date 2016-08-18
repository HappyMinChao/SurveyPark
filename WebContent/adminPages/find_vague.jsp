<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	//根据用户名模糊查询付费用户， 并按用户名排序， 注意在like条件是把整个参数作为变量进行设置
	<s:include value="/resources_jsp/admin_top.jsp"/>
	<s:include value="/resources_jsp/top.jsp"/>
	<s:form>
		请输入查询条件：<s:text name="findName"></s:text>
		请输入查询条件：<s:text name="findName"></s:text>
	</s:form>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>

</body>
</html>