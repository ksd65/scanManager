<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			if (${type != 1}){
				var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
				addRow("#treeTableList", tpl, ${fns:toJson(list)}, rootId, true);
				$("#treeTable").treeTable({expandLevel : 5});
			}
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">机构列表</a></li>
		<shiro:hasPermission name="sys:office:edit"><li><a href="${ctx}/sys/office/form?parent.id=${office.id}">机构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="office" action="${ctx}/sys/office/list?type=1" method="post" class="breadcrumb form-search ">
		<ul class="ul-form">
			<li><label>机构编号：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<!--  
			<li class="clearfix"></li>
			-->
			<li><label>机构名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			
			<li><label>代理商类型：</label>
				<form:select id="agtType" path="agtType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('sys_agt_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${type != 1}">
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>机构编号</th><th>机构名称</th><th>代理商类型</th><th>归属区域</th><th>机构类型</th><th>负责人</th><th>联系电话</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/office/form?id={{row.id}}">{{row.code}}</a></td>
			<td>{{row.name}}</td>
			<td>{{row.agtType}}</td>
			<td>{{row.area.name}}</td>
			<td>{{dict.type}}</td>
			<td>{{row.master}}</td>
			<td>{{row.phone}}</td>
			<shiro:hasPermission name="sys:office:edit"><td>
				<a href="${ctx}/sys/office/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
				<%--
				<a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级机构</a> 
				--%>
			</td></shiro:hasPermission>
		</tr>
	</script>
	</c:if>
	<c:if test="${type == 1}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr><th>机构编号</th><th>机构名称</th><th>归属区域</th><th>机构类型</th><th>负责人</th><th>联系电话</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr>
		<c:forEach items="${list}" var="office">
			<td><a href="${ctx}/sys/office/form?id=${row.id}">${office.code}</a></td>
			<td>${office.name}</td>
			<td>${office.area.name}</td>
			<td>${office.type}</td>
			<td>${office.master}</td>
			<td>${office.phone}</td>
			<shiro:hasPermission name="sys:office:edit"><td>
				<a href="${ctx}/sys/office/form?id=${office.id}">修改</a>
				<a href="${ctx}/sys/office/delete?id=${office.id}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
				<%--
				<a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级机构</a> 
				--%>
			</td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>