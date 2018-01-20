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
			if(days+1>7){
				alert("查询日期跨度不能超过7天");
				return false;
			}
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/debitNote/");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/debitNote/">交易订单列表</a></li>
		<!--  
		<shiro:hasPermission name="trade:debitNote:edit"><li><a href="${ctx}/trade/debitNote/form">交易明细添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="debitNote" action="${ctx}/trade/debitNote/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${debitNote.office.id }"/>
		<input type="hidden" name="his" value="${his}">
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${debitNote.office.id}" labelName="office.name" labelValue="${debitNote.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<%-- <li><label>商户编号：</label>
				<form:input id="memberCode" path="memberCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
		<!-- 	<li><label>手机号：</label>
				<form:input id="mobilePhone" path="mobilePhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li> -->
			<%-- <li><label>平台流水号：</label>
				<form:input path="ptSerialNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
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
				<form:select id="txnType" path="txnType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('txn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>交易状态：</label>
				<form:select id="respType" path="respType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('debit_resp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		 <!-- 	<li><label>结算方式：</label>
				<form:select id="settleType" path="settleType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('settle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> 
			 -->
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
				<th>交易状态</th>
				<th>失败原因</th>
				<th>订单时间</th>
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
					<c:choose>
						<c:when test="${debitNote.status=='0' }"><font color="red">未支付</font></c:when>
						<c:when test="${debitNote.status=='1' }"><font color="blue">支付成功</font></c:when>
						<c:when test="${debitNote.status=='3' }"><font color="red">不确定</font></c:when>
						<c:otherwise><font color="red">支付失败</font></c:otherwise>
					</c:choose>
					
				</td>
				<td>
					<c:choose>
						<c:when test="${debitNote.status=='2'||debitNote.status=='10'||debitNote.status=='11' }">
							${debitNote.respMsg}
						</c:when>
						<c:otherwise>
							${fns:getDictLabel(debitNote.status,'debit_status','')}
						</c:otherwise>
					</c:choose>
					
				</td>
				<td>
					<fmt:formatDate value="${debitNote.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
