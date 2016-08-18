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
			<s:if test="#request.authList == null || #request.authList.empty">
				<tr>
					<td>系统中没有权限！</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td>权限名称</td>
				<td>选择分配</td>
			</tr>
			<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
			<%-- 遍历过程中栈顶是Survey对象 --%>
			<s:form action="RoleAction_allocateAuthority" namespace="/Admin">
			
				<s:hidden name="roleId" value="%{#parameters.roleId}"></s:hidden>
				<s:iterator value="#request.authList">
					<tr>
						<td>
							<s:property value="authorityName"/>
						</td>
						
						<td>
							<!-- 全部的id：1,2,3,4,5,6 -->
							<!-- 要删除的id：2,3,6 -->
							<!-- 在Action类中接收的方式：List<Integer> resIdList -->
							<input <s:property value="#request.authIdValueList.contains(authorityId)? 'checked=checked':''"/> type="checkbox" name="authIdList" value="<s:property value="authorityId"/>" id="CheckBox<s:property value="authorityId"/>"/>
							<label for="CheckBox<s:property value="authorityId"/>">选择</label>
						</td>
					
					</tr>	
				</s:iterator>
				<tr>
					<td colspan="4" align="center">
						<s:submit value="确认分配"></s:submit>
					</td>
				</tr>
			</s:form>
			</s:else>
			
			<tr>
				<td colspan="5" align="center">
					<!-- 动态设置翻页超链接的URL地址：名称空间+ActionName
					set标签在没有指定scope属性时，是直接保存到Map栈中 -->
					<s:set value="'AuthorityAction_authorityList'" var="pageActionName"/>
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