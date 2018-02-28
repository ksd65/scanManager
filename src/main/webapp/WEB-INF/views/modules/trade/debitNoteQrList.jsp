<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易订单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		
		getPayeeList();
	});
		function page(n,s){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var day1 = new Date(beginTime);
			var day2 = new Date(endTime);
			var days=Math.floor((day2-day1)/(24*3600*1000));
			if(days+1>7){
				alert("查询日期跨度不能超过7天");
				return false;
			}
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/debitNote/qrOrderList");
			$("#searchForm").submit();
        	return false;
        }
		
		function getPayeeList(){
			var payType = $("#txnType").val();
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
		<li class="active"><a href="${ctx}/trade/debitNote/qrOrderList">待处理订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="debitNote" action="${ctx}/trade/debitNote/qrOrderList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${debitNote.office.id }"/>
		<input type="hidden" name="his" value="${his}">
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${debitNote.office.id}" labelName="office.name" labelValue="${debitNote.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
		
			<li><label>交易日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${debitNote.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'#F{$dp.$D(\'endTime\');}'});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${debitNote.endTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'%y-%M-%d'});"/>
			</li>
			<li><label>交易类型：</label>
				<form:select id="txnType" path="txnType" class="input-medium" onchange="getPayeeList();">
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
		<!--	<li><label>交易状态：</label>
				<form:select id="respType" path="respType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('debit_resp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>-->
			<li><label>收款备注：</label>
				<form:input id="remarks" path="remarks" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>订单号：</label>
				<form:input id="orderCode" path="orderCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户订单号：</label>
				<form:input id="orderNumOuter" path="orderNumOuter" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<!--  <th>手机号</th>
				<th>所属机构</th>-->
				<th>交易金额</th>
				<th>商户费率</th>
				<th>交易订单号</th>
				<th>商户订单号</th>
			    <th>交易方式 </th> 
				<th>交易类型 </th>
				<th>收款人</th>
				<th>收款备注</th>
				<th>订单时间</th>
				<shiro:hasPermission name="trade:debitNote:qrOrder"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="debitNote">
			<tr>
				<td>
					${debitNote.memberCode}
				</td>
				<td>
					${debitNote.member.name}
				</td>
			<!-- 	<td>
					${debitNote.mobilePhone}
				</td>
				<td>
					${debitNote.office.name}-->
				</td>
				<td>
					${debitNote.money}
				</td>
				<td>
					${debitNote.memberTradeRate}
				</td>
				 
				<td>
					${debitNote.orderCode}
				</td>
				<td>
					${debitNote.orderNumOuter}
				</td>
			 	<td>
					${fns:getDictLabel(debitNote.txnMethod,'txn_method',debitNote.txnMethod)}
				</td> 
				<td>
					${fns:getDictLabel(debitNote.txnType,'txn_type',debitNote.txnType)}
				</td>
				
				
				<td>
					${debitNote.payAccount}(${debitNote.payUserName})
				</td>
				<td>
					${debitNote.remarks}
					
				</td>
				<td>
					<fmt:formatDate value="${debitNote.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="trade:debitNote:qrOrder"><td>
					
    				<a href="${ctx}/trade/debitNote/deal?id=${debitNote.id}&status=1" onclick="return confirmx('确认该订单支付成功吗？', this.href)">支付成功</a>
    				&nbsp;&nbsp;<a href="${ctx}/trade/debitNote/deal?id=${debitNote.id}&status=2" onclick="return confirmx('确认该订单支付失败吗？', this.href)">支付失败</a>
    				&nbsp;&nbsp;<a href="${ctx}/trade/debitNote/deal?id=${debitNote.id}&status=0" onclick="return confirmx('确认该订单未支付吗？', this.href)">未支付释放码</a>
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	

<script type="text/javascript">

</script>
</body>

</html>
