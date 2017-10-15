<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表统计</title>
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
		
		// 提交表单
		function submitForm(type){
			$("#type").val(type);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">单月交易统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="payBillMonthRpt" action="${ctx}/report/payBillMonthRpt/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${payBillMonthRpt.office.id}" labelName="office.name" labelValue="${payBillMonthRpt.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>月份：</label>
				<form:input id="month" path="month" type="text" readonly="readonly" maxlength="6" class="input-medium Wdate"
					value="${payBillMonthRpt.month}"
					onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('0')" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('1')" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>所属机构</th>
				<th>D0交易笔数</th>
				<th>D0交易额</th>
				<th>T1交易笔数</th>
				<th>T1交易额</th>
				
				<th>快捷(有积分)交易笔数</th>
				<th>快捷(有积分)交易额</th>
				<th>快捷(无积分)交易笔数</th>
				<th>快捷(无积分)交易额</th>
				
				<th>交易总笔数</th>
				<th>交易总额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payBillMonthRpt">
			<tr>
				<td>
					${payBillMonthRpt.month}
				</td>
				<td>
					${payBillMonthRpt.office.name}
				</td>
				<td>
					${payBillMonthRpt.d0Count}
				</td>
				<td>
					${payBillMonthRpt.d0Amount}
				</td>
				<td>
					${payBillMonthRpt.t1Count}
				</td>
				<td>
					${payBillMonthRpt.t1Amount}
				</td>
				
				<td>
					${payBillMonthRpt.mlJfCount}
				</td>
				<td>
					${payBillMonthRpt.mlJfAmount}
				</td>
				<td>
					${payBillMonthRpt.mlWjfCount}
				</td>
				<td>
					${payBillMonthRpt.mlWjfAmount}
				</td>
				
				<td>
					${payBillMonthRpt.totalCount}
				</td>
				<td>
					${payBillMonthRpt.totalAmount}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>