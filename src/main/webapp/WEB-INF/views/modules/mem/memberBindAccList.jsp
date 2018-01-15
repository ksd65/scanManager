<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>提现绑卡管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	$("#btnExport").click(function(){
		top.$.jBox.confirm("确认要导出流水数据吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#searchForm").attr("action","${ctx}/mem/memberBindAcc/export");
				$("#searchForm").submit();
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
	});
	
});
	function page(n, s) {
        $("#searchForm").attr("action","${ctx}/mem/memberBindAcc/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mem/memberBindAcc/">提现银行卡列表</a></li>
		<li><a href="${ctx}/mem/memberBindAcc/form">提现银行卡添加</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="memberBindAcc"
		action="${ctx}/mem/memberBindAcc/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<!-- <li><label>手机号码：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${memberBindAcc.office.id}" labelName="office.name" labelValue="${memberBindAcc.office.name}"
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li> -->
			<li><label>绑定时间：</label>
				<input id="bindBeginTime" name="bindBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${memberBindAcc.bindBeginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="bindEndTime" name="bindEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${memberBindAcc.bindEndTime}"
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
				<th>商户编号</th>
				<th>商户名称</th>
				<th>手机号码</th>
				<th>所属机构</th>
				<th>发卡行</th>
				<th>发卡分行</th>
				<th>联行号</th>
				<th>持卡人</th>
				<th>银行卡号</th>
				<th>预留手机号</th>
				<th>绑定日期</th>
				<th>绑定时间</th>
				<shiro:hasPermission name="mem:memberBindAcc:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="memberBindAcc">
				<tr>
					<td>${memberBindAcc.member.code}</td>
					<td>${memberBindAcc.member.name }</td>
					<td>${memberBindAcc.member.mobilePhone }</td>
					<td>${memberBindAcc.office.name }</td>
					<td> ${memberBindAcc.bankName }</td>
					<td> ${memberBindAcc.subName }</td>
					<td> ${memberBindAcc.subId }</td>
					<td> ${memberBindAcc.name }</td>
					<td> ${memberBindAcc.acc }</td>
					<td> ${memberBindAcc.mobilePhone }</td>
					
					<td><fmt:formatDate value="${memberBindAcc.createDate}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${memberBindAcc.createDate}" pattern="HH:mm:ss"/></td>
					<shiro:hasPermission name="mem:memberBindAcc:view"><td>
    				<a href="${ctx}/mem/memberBindAcc/delete?id=${memberBindAcc.id}" onclick="return confirmx('确认要删除该银行卡吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>