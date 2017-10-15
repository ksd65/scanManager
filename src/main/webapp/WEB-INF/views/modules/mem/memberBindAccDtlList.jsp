<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商户绑卡流水管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	$("#btnExport").click(function(){
		top.$.jBox.confirm("确认要导出流水数据吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#searchForm").attr("action","${ctx}/mem/memberBindAccDtl/export");
				$("#searchForm").submit();
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
	});
	
});
	function page(n, s) {
        $("#searchForm").attr("action","${ctx}/mem/memberBindAccDtl/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mem/memberBindAccDtl/">商户绑卡流水列表</a></li>
		<shiro:hasPermission name="mem:memberBindAccDtl:edit">
			<li><a href="${ctx}/mem/memberBindAccDtl/form">商户绑卡流水添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberBindAccDtl"
		action="${ctx}/mem/memberBindAccDtl/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>手机号码：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${memberBindAccDtl.office.id}" labelName="office.name" labelValue="${memberBindAccDtl.office.name}"
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>绑定时间：</label>
				<input id="beginBindTime" name="beginBindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${memberBindAccDtl.beginBindTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="endBindTime" name="endBindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${memberBindAccDtl.endBindTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<%---<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
			 --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>手机号码</th>
				<th>一级所属机构</th>
				<th>所属机构</th>
				<th>持卡人</th>
				<th>发卡行</th>
				<th>银行卡号</th>
				<th>预留手机号</th>
				<th>绑定类型</th>
				<th>绑卡手续费</th>
				<th>绑定日期</th>
				<th>绑定时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="memberBindAccDtl">
				<tr>
					<td>${memberBindAccDtl.member.code}</td>
					<td>${memberBindAccDtl.member.name }</td>
					<td>${memberBindAccDtl.member.mobilePhone }</td>
					<td>${memberBindAccDtl.agentNameLevel1 }</td>
					<td>${memberBindAccDtl.office.name }</td>
					<td> ${memberBindAccDtl.name }</td>
					<td> ${memberBindAccDtl.bankName }</td>
					<td> ${memberBindAccDtl.acc }</td>
					<td> ${memberBindAccDtl.mobilePhone }</td>
					<td>${fns:getDictLabel(memberBindAccDtl.bindType,'bind_type','')}</td>
					<td>0.5元</td>
					<td><fmt:formatDate value="${memberBindAccDtl.createDate}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${memberBindAccDtl.createDate}" pattern="HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>