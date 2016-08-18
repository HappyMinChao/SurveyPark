<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>

<script type="text/javascript">
	$(function(){
		
		$("#goBack").click(function(){
			
			window.history.back();
			
		});
		
	});
</script>



</head>
<body>
	
	<s:include value="/resources_jsp/top.jsp"/>
	<s:debug></s:debug>
	
	<s:if test="hasActionErrors()">
	-------------------------
		<s:actionerror/>
		<button id="goBack">返回上一页</button>
	</s:if>
	<s:else>
		<s:if test="exception != null">
			<div style="text-align: center; color:red;">
				<h3>
					<s:property value="getText(exception.class.name)"/>
				</h3>
				<br/><br/><br/>
				<button id="goBack">返回上一页</button>
				<br/><br/>
			</div>
		</s:if>
		
		<s:else>
			<div style="text-align: center;">
				<h3>操作成功！</h3>
				<s:a action="SurveyAction_myUncompletedSurvey?currentPageNo=1"
					 namespace="/Guest">
					 返回调查列表
				</s:a>
				<br/><br/>
			</div>
		</s:else>
	</s:else>
	
	
	<s:include value="/resources_jsp/bottom.jsp"/>

</body>
</html>