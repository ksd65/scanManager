<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>E码付管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#type").val("0");
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function submitForm(type){
			$("#type").val(type);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ecode/epayCode/">库存列表</a></li> 
		<shiro:hasPermission name="ecode:epayCode:edit"><li><a href="${ctx}/ecode/epayCode/form">库存划拨</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="epayCode" action="${ctx}/ecode/epayCode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden" value="0"/>
		<ul class="ul-form">
			<li><label>E码付编号：</label>
				<form:input path="payCode" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('ecode_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>划拨批次号：</label>
				<form:input path="batchNo" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>生成批次号：</label>
				<form:input path="createBatchNo" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('0')" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('1')" value="下载二维码"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>E码付编号</th>
				<th>码状态</th>
				<th>所属机构</th>
				<th>D0交易费率</th>
				<th>D0提现手续费</th>
				<th>T+1交易费率</th>
				<th>T+1提现手续费</th>
				<th>快捷支付费率(有积分)</th>
				<th>快捷支付提现费(有积分)</th>
				<th>快捷支付费率(无积分)</th>
				<th>快捷支付提现费(无积分)</th>
				<th>划拨批次号</th>
				<th>生成批次号</th>					
				<th>更新时间</th>
				<!--  
				<shiro:hasPermission name="ecode:epayCode:edit"><th>操作</th></shiro:hasPermission>
				-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="epayCode">
			<tr>
				<td><a href="${ctx}/ecode/epayCode/form?id=${epayCode.id}">
					${epayCode.payCode}
				</a></td>
				<td>
					${fns:getDictLabel(epayCode.status,'ecode_status','E码付状态')}
				</td>
				<td>
					${epayCode.office.name}
				</td>
				<td>${epayCode.t0TradeRate}</td>
				<td>${epayCode.t0DrawFee}</td>
				<td>${epayCode.t1TradeRate}</td>
				<td>${epayCode.t1DrawFee}</td>
				<td>${epayCode.mlJfRate}</td>
				<td>${epayCode.mlJfFee}</td>
				<td>${epayCode.mlWjfRate}</td>
				<td>${epayCode.mlWjfFee}</td>
				<td>
					${epayCode.batchNo}
				</td>
				<td>
					${epayCode.createBatchNo}
				</td>
				<td>
					<fmt:formatDate value="${epayCode.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!--  
				<shiro:hasPermission name="ecode:epayCode:edit"><td>
    				<a href="${ctx}/ecode/epayCode/form?id=${epayCode.id}">修改</a>
					<a href="${ctx}/ecode/epayCode/delete?id=${epayCode.id}" onclick="return confirmx('确认要删除该商户二维码吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>