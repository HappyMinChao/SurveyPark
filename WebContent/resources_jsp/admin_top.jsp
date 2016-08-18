<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.atguigu.com/survey" prefix="atguigu" %>
<div id="guestInfoBlock">
	<div id="guestInfoContent">
		<s:if test="#session.loginAdmin == null">
			管理员未登录
		</s:if>
		<s:else>
			管理员账号：[<s:property value="#session.loginAdmin.adminName"/>]
			<s:a namespace="/Admin" action="AdminAction_logout">退出登录</s:a>
		</s:else>
	</div>
</div>
    
<div id="adminNavigation" style="text-align: center;font-size: 18px;font-style: inherit;">
	[<s:a namespace="/Admin" action="ExcelAction_showAllSurvey">Excel</s:a>]
	[<s:a namespace="/Admin" action="ResourceAction_showAllResources">资源列表</s:a>]
	[<s:a namespace="/Admin" action="AuthorityAction_toCreatePage">创建权限</s:a>]
	[<s:a namespace="/Admin" action="AuthorityAction_authorityList">权限列表</s:a>]
	[<s:a namespace="/Admin" action="RoleAction_toSave">创建角色</s:a>]
	[<s:a namespace="/Admin" action="RoleAction_showRoleList">角色列表</s:a>]
	[<s:a namespace="/Admin" action="AdminAction_showAdminList">管理员列表</s:a>]
	[<s:a namespace="/Admin" action="AdminAction_calculateCode">统一计算权限码</s:a>]
		[<s:a namespace="/Admin" action="AdminAction_generateAdmin">批量生成管理员账号</s:a>]
	[<s:a namespace="/Admin" action="LogAction">显示日志</s:a>]
	[<s:a namespace="/Admin" action="LogAction">模糊查询用户</s:a>]
</div>

<div class="block-div" id="headTitle">
	<img src="<s:url value="/resources_static/surveyLogo.png"/>"/>
</div>