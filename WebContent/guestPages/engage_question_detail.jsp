<%@ page language="java" contentType="text/html; chasrset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.atguigu.com/survey" prefix="atguigu" %>
<table class="dashedTable">
	<s:if test="questionSet == null || questionSet.size() == 0">
		<tr>
			<td>当前包裹还没有创建问题！</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator value="questionSet">
			<%-- 遍历问题集合 --%>
			<%-- 栈顶对象：Question --%>
			<%-- 一.Question的基本信息 --%>
			<tr>
				<td><s:property value="questionName"/> &nbsp; <s:property value="questionId"/> </td>
				
			</tr>
			<tr>
				<td>
					<atguigu:generateQuestion currentBagIdOGNL="bagId"/>
				</td>
			</tr>
			
		</s:iterator>
	</s:else>
</table>