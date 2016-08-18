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
	
	<s:if test="hasActionErrors()">
		<s:actionerror/>
	</s:if>
	<s:else>
		完成包裹成功。
	</s:else>
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>