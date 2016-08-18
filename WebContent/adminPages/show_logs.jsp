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
		<div class="locationDiv">所有的资源列表</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable" style="text-align: center;">
			<s:if test="#request.page == null || #request.page.list.empty">
				<tr>
					<td>系统中没有权限！</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td>日志Id</td>
				<td>操作者</td>
				<td>操作时间</td>
				<td>方法类型</td>
				<td>方法名字</td>
				<td>方法参数</td>
				<td>方法返回值</td>
				<td>方法结果</td>
			</tr>
			<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
			<%-- 遍历过程中栈顶是Survey对象 --%>
			<s:form action="AuthorityAction_delete" namespace="/Admin">
				<s:iterator value="#request.page.list">
					<tr>
						<td> <s:property value="logId"/> </td>
						<td> <s:property value="operate"/> </td>
						<td> <s:property value="operateTime"/> </td>
						<td> <s:property value="methodType"/> </td>
						<td> <s:property value="methodName"/> </td>
						<td> <s:property value="methodArgs"/> </td>
						<td> <s:property value="methodReturn"/> </td>
						<td> <s:property value="methodResultMesg"/> </td>
					
					</tr>	
				</s:iterator>
				
			</s:form>
			</s:else>
			
			<tr>
				<td colspan="5" align="center">
					<!-- 动态设置翻页超链接的URL地址：名称空间+ActionName
					set标签在没有指定scope属性时，是直接保存到Map栈中 -->
					<s:set value="'LogAction'" var="pageActionName"/>
					<s:set value="'/Admin'" var="pageNamespace"/>
					<s:include value="/resources_jsp/pageNavigator.jsp"/>
					<s:debug/>
				</td>
			</tr>
			
		</table>
	</div>
	
	<s:include value="/resources_jsp/index_bottom.jsp"/>

</body>
</html>