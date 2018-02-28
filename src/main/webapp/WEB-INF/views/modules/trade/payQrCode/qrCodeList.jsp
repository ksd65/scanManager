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
        $("#searchForm").attr("action","${ctx}/trade/payQrCode/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	function getPayeeList(){
		var payType = $("#payType").val();
		var subHtml = "<option value=\"\">请选择</option>";
		
		$.ajax({
			url:"${ctx }/trade/payee/getPayeeList",
			data:{payType:payType},
			type:'post',
			cache:false, 
			async:false,
			dataType:'json',
			success:function(data) {
				
				var list = data.payeeList;
				for(var i=0;i<list.length;i++){
					subHtml = subHtml+ "<option value=\""+list[i].id+"\">"+list[i].payAccount+"("+list[i].userName+")"+"</option>";	
				}
				
				$("#payeeId").html(subHtml);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
		        alert("请求出错");
		    }
		});

	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/payQrCode/">收款码列表</a></li>
		<li><a href="${ctx}/trade/payQrCode/form">收款码添加</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="payQrCode"
		action="${ctx}/trade/payQrCode/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>交易类型：</label>
				<form:select id="payType" path="payType" class="input-medium" onchange="getPayeeList();">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('qr_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			 <li><label>收款人：</label>
				<form:select path="payeeId" class="input-medium" id="payeeId" >
					<form:option value="" label="请选择"/>
					
				</form:select>
			</li>
			<li><label>交易金额：</label>
				<form:input path="money" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>收款备注：</label>
				<form:input path="qrCodeRemark" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select id="status" path="status" class="input-medium" >
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('qrcode_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payQrCode.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payQrCode.endTime}"
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
				<th>交易金额</th>
				<th>收款人</th>
				<th>收款备注</th>
				<th>交易类型</th>
				<th>创建时间</th>
				<th>状态</th>
				
				<shiro:hasPermission name="trade:payQrCode:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="payQrCode">
				<tr>
					<td>${payQrCode.money}</td>
					<td>${payQrCode.payAccount }(${payQrCode.userName })</td>
					<td>${payQrCode.qrCodeRemark }</td>
					<td>${fns:getDictLabel(payQrCode.payType,'qr_pay_type',payQrCode.payType)}</td>
					<td><fmt:formatDate value="${payQrCode.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${fns:getDictLabel(payQrCode.status,'qrcode_status',payQrCode.status)}</td>
					<shiro:hasPermission name="trade:payQrCode:view"><td>
					<c:if test="${payQrCode.status=='0' }">
    				<a href="${ctx}/trade/payQrCode/delete?id=${payQrCode.id}" onclick="return confirmx('确认要删除该收款码吗？', this.href)">删除</a>
    				</c:if>
				</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>