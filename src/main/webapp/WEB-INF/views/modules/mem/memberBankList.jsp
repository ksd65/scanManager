<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户绑卡信息查询</title>
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
		<li class="active"><a href="${ctx}/mem/member/banks">商户绑卡信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/mem/member/banks" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${member.office.id}" labelName="office.name" labelValue="${member.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>绑定日期：</label>
				<input id="beginBindBankTime" name="beginBindBankTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${member.beginBindBankTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="endBindBankTime" name="endBindBankTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${member.endBindBankTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>所属机构</th>
				<th>手机号码</th>
				<th>持卡人</th>
				<th>发卡行</th>
				<th>银行卡号</th>
				<th>预留手机号</th>
				<th>绑定日期</th>
				<th>绑定时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
				<td>
					${member.code}
				</td>
				<td>
					${member.name}
				</td>
				<td>
					${member.office.name}
				</td>
				<td>
					${member.mobilePhone}
				</td>
				<td>${member.bindBankUsername}</td>
				<td>${member.bindBankName }</td>
				<td>${member.bindBankNumber }</td>
				<td>${member.bindBankPhone }</td>
				<td>
					<fmt:formatDate value="${member.bindBankTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${member.bindBankTime}" pattern="HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>