<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易订单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		function page(n,s){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var day1 = new Date(beginTime);
			var day2 = new Date(endTime);
			var days=Math.floor((day2-day1)/(24*3600*1000));
			if(days+1>1){
				alert("查询时间跨度不能超过1天");
				return false;
			}
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/analysis/member");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/trade/analysis/route">通道交易分析</a></li>
		<li><a href="${ctx}/trade/analysis/routeMerchant">通道子商户交易分析</a></li>
		<li  class="active"><a href="${ctx}/trade/analysis/member">下游商户交易分析</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeAnalysis" action="${ctx}/trade/analysis/member" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${tradeAnalysis.office.id }"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${tradeAnalysis.office.id}" labelName="office.name" labelValue="${tradeAnalysis.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li><label>交易时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeAnalysis.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,maxDate:'#F{$dp.$D(\'endTime\');}'});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeAnalysis.endTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,maxDate:'%y-%M-%d 23:59:59'});"/>
			</li>
			<li><label>交易方式：</label>
				<form:select id="txnMethod" path="txnMethod" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('txn_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>交易类型：</label>
				<form:select id="txnType" path="txnType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('txn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>交易方式 </th>
				<th>交易类型 </th>
				<th>成功笔数</th>
				<th>总失败笔数</th>
				<th>上游通道失败笔数</th>
				<th>成功率 </th>
				<th>上游通道成功率 </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeAnalysis">
			<tr>
				<td>
					${tradeAnalysis.memberCode}
				</td>
				<td>
					${tradeAnalysis.memberName}
				</td>
				<td>
					${fns:getDictLabel(tradeAnalysis.txnMethod,'txn_method',tradeAnalysis.txnMethod)}
				</td>
				<td>
					${fns:getDictLabel(tradeAnalysis.txnType,'txn_type',tradeAnalysis.txnType)}
				</td>
				<td>
					${tradeAnalysis.successCount}
				</td>
				 
				<td>
					${tradeAnalysis.failCount}
				</td>
				<td>
					${tradeAnalysis.routeFailCount}
				</td>
				<td>
					${tradeAnalysis.platSuccessPer}%
				</td>
				<td>
					${tradeAnalysis.rountSuccessPer}%
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
