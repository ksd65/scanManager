<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OEM代理商配置信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/agentConfig/">OEM代理商配置信息列表</a></li>
		<shiro:hasPermission name="sys:agentConfig:edit"><li><a href="${ctx}/sys/agentConfig/form">OEM代理商配置信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="agentConfig" action="${ctx}/sys/agentConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>代理商编号：</label>
				<form:input path="agentId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>代理商名称：</label>
				<form:input path="agentName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>代理商简称：</label>
				<form:input path="agentShortName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代理商编号</th>
				<th>代理商名称</th>
				<th>代理商简称</th>
				<th>代理商版权信息</th>
				<th>URL域名</th>
				<th>地址配置名</th>
				<th>背景图片名</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:agentConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="agentConfig">
			<tr>
				<td><a href="${ctx}/sys/agentConfig/form?id=${agentConfig.id}">
					${agentConfig.agentId}
				</a></td>
				<td>
					${agentConfig.agentName}
				</td>
				<td>
					${agentConfig.agentShortName}
				</td>
				<td>
					${agentConfig.agentCopyright}
				</td>
				<td>
					${agentConfig.url}
				</td>
				<td>
					${agentConfig.epaycodeUrlName}
				</td>
				<td>
					${agentConfig.epaycodeImg}
				</td>
				<td>
					<fmt:formatDate value="${agentConfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:agentConfig:edit"><td>
    				<a href="${ctx}/sys/agentConfig/form?id=${agentConfig.id}">修改</a>
					<a href="${ctx}/sys/agentConfig/delete?id=${agentConfig.id}" onclick="return confirmx('确认要删除该OEM代理商配置信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>