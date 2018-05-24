<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>支付二维码</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	
	getPayeeList();
});
	function page(n, s) {
        $("#searchForm").attr("action","${ctx}/paytype/rule/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	

	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paytype/rule/">通道规则列表</a></li>
		<li><a href="${ctx}/paytype/rule/form">通道规则添加</a></li>
		
	</ul>
	
	<form:form id="searchForm" modelAttribute="payTypeRule"
		action="${ctx}/trade/payTypeRule/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input id="officeid" type="hidden" value="${payTypeRule.office.id }"/>
		
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${payTypeRule.office.id}" labelName="office.name" labelValue="${tradeDetail.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>交易方式：</label>
				<form:select id="payMethod" path="payMethod" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('rule_txn_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>交易类型：</label>
				<form:select id="payType" path="payType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('rule_txn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>商户名称</th>
				<th>商户编号</th>
				<th>交易方式</th>
				<th>交易类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="payTypeRule">
				<tr>
					<td>${payTypeRule.memberName }</td>
					<td>${payTypeRule.memberCode}</td>
					<td>${fns:getDictLabel(payTypeRule.payMethod,'rule_txn_method',payTypeRule.payMethod)}</td>
					<td>${fns:getDictLabel(payTypeRule.payType,'rule_txn_type',payTypeRule.payType)}</td>
				<td>	<a href="${ctx}/paytype/rule/detail?payMethod=${payTypeRule.payMethod}&payType=${payTypeRule.payType}&memberId=${payTypeRule.memberId}">详情</a>
    					<a href="${ctx}/paytype/rule/edit?payMethod=${payTypeRule.payMethod}&payType=${payTypeRule.payType}&memberId=${payTypeRule.memberId}">修改</a>
						<a href="${ctx}/paytype/rule/delete?payMethod=${payTypeRule.payMethod}&payType=${payTypeRule.payType}&memberId=${payTypeRule.memberId}" onclick="return confirmx('确认要删除该通道规则吗？', this.href)">删除</a>
    				
				</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>