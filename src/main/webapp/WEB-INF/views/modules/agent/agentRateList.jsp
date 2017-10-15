<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理商成本配置</title>
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
		<li class="active"><a href="${ctx}/agent/agentRate/">代理商成本配置列表</a></li>
		<!--  
		<shiro:hasPermission name="agent:agentRate:edit"><li><a href="${ctx}/agent/agentRate/form">代理商成本配置添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="agentRate" action="${ctx}/agent/agentRate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!--  
			<li><label>机构编号：</label>
				<sys:treeselect id="office" name="office.id" value="${agentRate.office.id}" labelName="office.name" labelValue="${agentRate.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			-->
			<li><label>代理商名称：</label><form:input id="agtName" path="agtName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代理商名称</th>
				<th>D0单笔提现费</th>
				<th>D0交易费率</th>
				<th>T1提现费</th>
				<th>T1交易手续费扣率</th>
				<th>快捷(有积分)提现费</th>
				<th>快捷(有积分)费率</th>
				<th>快捷(无积分)提现费</th>
				<th>快捷(无积分)费率</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="agent:agentRate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="agentRate">
			<tr>
				<td>
					${agentRate.office.name}
				</td>
				<td>
					${agentRate.t0DrawFee}
				</td>
				<td>
					${agentRate.t0TradeRate}
				</td>
				<td>
					${agentRate.t1DrawFee}
				</td>
				<td>
					${agentRate.t1TradeRate}
				</td>
				
				<td>
					${agentRate.bonusQuickFee}
				</td>
				<td>
					${agentRate.bonusQuickRate}
				</td>
				<td>
					${agentRate.quickFee}
				</td>
				<td>
					${agentRate.quickRate}
				</td>
				<td>
					<fmt:formatDate value="${agentRate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${agentRate.remarks}
				</td>
				<shiro:hasPermission name="agent:agentRate:edit"><td>
    				<a href="${ctx}/agent/agentRate/form?id=${agentRate.id}">配置</a>
    				<!--  
					<a href="${ctx}/agent/agentRate/delete?id=${agentRate.id}" onclick="return confirmx('确认要删除该代理商成本配置吗？', this.href)">删除</a>
					-->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>