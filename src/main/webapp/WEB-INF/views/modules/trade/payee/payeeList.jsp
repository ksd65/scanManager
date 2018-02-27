<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>收款人</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	
	
});
	function page(n, s) {
        $("#searchForm").attr("action","${ctx}/trade/payee/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/payee/">收款人列表</a></li>
		<li><a href="${ctx}/trade/payee/form">收款人添加</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="payee"
		action="${ctx}/trade/payee/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			 <li><label>收款人：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>收款账号：</label>
				<form:input path="payAccount" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>收款类型：</label>
				<form:select id="payType" path="payType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('qr_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payee.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payee.endTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收款账号</th>
				<th>收款人姓名</th>
				<th>收款类型</th>
				<th>创建时间</th>
				
				<shiro:hasPermission name="trade:payee:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="payee">
				<tr>
					<td>${payee.payAccount}</td>
					<td>${payee.userName }</td>
					<td>${fns:getDictLabel(payee.payType,'qr_pay_type',payee.payType)}</td>
					<td><fmt:formatDate value="${payee.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<shiro:hasPermission name="trade:payee:view"><td>
					<a href="${ctx}/trade/payee/delete?id=${payee.id}" onclick="return confirmx('确认要删除该收款人吗？', this.href)">删除</a>
    			</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>