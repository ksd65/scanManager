<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预收款管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/prepay/");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/prepay/">预收款列表</a></li>
		<!--  
		<shiro:hasPermission name="trade:debitNote:edit"><li><a href="${ctx}/trade/prepay/form">交易明细添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="prePayStatistics" action="${ctx}/trade/prepay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${prePayStatistics.office.id }"/>
		<input type="hidden" name="his" value="${his}">
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${prePayStatistics.office.id}" labelName="office.name" labelValue="${prePayStatistics.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
		  
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>商户费率</th>
				<th>预收款</th>
				<th>剩余预收款</th>
				<th>成功交易金额</th>
				<th>待处理金额</th>
				<th>剩余可交易金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="prePayStatistics">
			<tr>
				<td>
					${prePayStatistics.member.code}
				</td>
				<td>
					${prePayStatistics.member.name}
				</td>
				<td>
					${prePayStatistics.tradeRate}
				</td>
				<td>
					${prePayStatistics.preMoney}
				</td>
				<td>
					<fmt:formatNumber type="number" value="${prePayStatistics.leftPreMoney}" pattern="0.00" maxFractionDigits="2"/>
				</td>
				<td>
					${prePayStatistics.tradeMoney}
				</td>
				 
				<td>
					${prePayStatistics.undealMoney}
				</td>
				<td>
					<fmt:formatNumber type="number" value="${prePayStatistics.leftTradeMoney}" pattern="0.00" maxFractionDigits="2"/>
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	

<script type="text/javascript">

</script>
</body>

</html>
