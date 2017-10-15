<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户审核管理</title>
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
		<li class="active"><a href="${ctx}/reg/register/">商户注册列表</a></li>
		<!--  
		<shiro:hasPermission name="reg:register:edit"><li><a href="${ctx}/reg/register/form">商户审核添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="register" action="${ctx}/reg/register/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>联系人：</label>
				<form:input path="contact" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="certNbr" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<!--  
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${register.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			-->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>所属机构</th>
				<th>手机号码</th>
				<th>商户名称</th>
				<th>注册状态</th>
				<th>联系人</th>
				<th>身份证号</th>
				<th>E码付编号</th>
				<!--  
				<th>审核人</th>
				-->
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="reg:register:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="register">
			<tr>
				<td><a href="${ctx}/reg/register/form?id=${register.id}">
					${register.code}
				</a></td>
				<td>
					${register.office.name}
				</td>
				<td>
					${register.mobilePhone}
				</td>
				<td>
					${register.name}
				</td>
				<td>
					${fns:getDictLabel(register.status,'register_status','审核状态')}
				</td>
				<td>
					${register.contact}
				</td>
				<td>
					${register.certNbr}
				</td>
				<td>
					${register.payCode}
				</td>
				<!--  
				<td>
					${register.updateBy.name}
				</td>
				-->
				<td>
					<fmt:formatDate value="${register.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${register.remarks}
				</td>
				<shiro:hasPermission name="reg:register:view"><td>
    				<a href="${ctx}/reg/register/detail?id=${register.id}">查看详情</a>
				</td></shiro:hasPermission>
				<!--
				<shiro:hasPermission name="reg:register:edit"><td>
    				<a href="${ctx}/reg/register/form?id=${register.id}">审核</a>
				</td></shiro:hasPermission>
				-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>