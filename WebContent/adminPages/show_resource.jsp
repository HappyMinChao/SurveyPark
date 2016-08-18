<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp"/>

<script type="text/javascript">

	/* $(function(){
		
		$(".resourceName").change(function(){
			var resourceName = $.trim(this.value);
			if(resourceName=""){
				this.value = this.defaultValue;
				return;
			}
			
			var resourceId = this.title;
			
			var url = "${pageContext.request.contextPath}/Admin/ResourceAction_update";
			var paramData = {"resourceId":resourceId,"resourceName":resourceName,"time:":new Date()};
			var type="json";
			var callBack = function(respData){
				alert(respData.message);
			};
			$.post(url,paramData,callBack,type);
		});
	});
 */
 
	$(function(){
		
		$(".resourceName").change(function(){
			
			var resourceName = $.trim(this.value);
			
			if(resourceName == "") {
				this.value = this.defaultValue;
				return ;
			}
			
			var resourceId = this.title;
			
			var url = "${pageContext.request.contextPath }/Admin/ResourceAction_update";
			var paramData = {"resourceId":resourceId,"resourceName":resourceName,"time":new Date()};
			var type = "json";
			var callBack = function(respData){
				
				alert(respData.message);
				
			};
			
			$.post(url,paramData,callBack,type);
			
		});
		
	});
</script>

</head>
<body>

	<s:include value="/resources_jsp/admin_top.jsp"/>
	
	<div class="block-div navigatorDiv">
		<div class="locationDiv">所有的资源列表</div>
	</div>
	
	<div class="block-div">
		<table class="dashedTable" style="text-align: center;"> 
			<s:if test="#request.resList == null || #request.resList.empty">
				<tr>
					<td>系统中还没有资源！</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td>资源名称</td>
				<td>资源翻译名称</td>
				<td>选择删除</td>
			</tr>
			<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
			<%-- 遍历过程中栈顶是Survey对象 --%>
			<s:form action="ResourceAction_delete" namespace="/Admin">
				<s:iterator value="#request.resList">
					<tr>
						<td>
							<s:property value="actionName"/>
						</td>
						<td>
							<input title="<s:property value="resourceId"/>" class="resourceName" name="resourceName" type="text" value="<s:property value="resourceName"/>"/>
						</td>
							<td>
								<!-- 全部的id：1,2,3,4,5,6 -->
								<!-- 要删除的id：2,3,6 -->
								<!-- 在Action类中接收的方式：List<Integer> resIdList -->
								<input type="checkbox" name="resIdList" value="<s:property value="resourceId"/>" id="CheckBox<s:property value="resourceId"/>"/>
								<label for="CheckBox<s:property value="resourceId"/>">选择</label>
							</td>
						
					</tr>	
				</s:iterator>
				<tr>
					<td colspan="4" align="center">
						<s:submit value="OK"></s:submit>
					</td>
				</tr>
				<s:submit value="删除"></s:submit>
			</s:form>
			</s:else>
			
			
			
		</table>
	</div>
	
	<s:include value="/resources_jsp/index_bottom.jsp"/>

</body>
</html>