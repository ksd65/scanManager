<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>快捷交易查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出交易数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/trade/tradeDetail/quick/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/tradeDetail/quick");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/tradeDetail/quick">快捷交易列表</a></li>
		<!--  
		<shiro:hasPermission name="trade:tradeDetail:edit"><li><a href="${ctx}/trade/tradeDetail/form">交易明细添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="tradeDetail" action="${ctx}/trade/tradeDetail/quick/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${tradeDetail.office.id }"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${tradeDetail.office.id}" labelName="office.name" labelValue="${tradeDetail.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<%-- <li><label>商户编号：</label>
				<form:input id="memberCode" path="memberCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<%--<li><label>手机号：</label>
				<form:input id="mobilePhone" path="mobilePhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li> --%>
			<%-- <li><label>平台流水号：</label>
				<form:input path="ptSerialNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>交易日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeDetail.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeDetail.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>交易类型：</label>
				<form:select id="txnType" path="txnType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:option value="6" label="快捷（有积分）"/>
					<form:option value="7" label="快捷（无积分）"/>
					<%-- <form:options items="${fns:getDictList('txn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<%--
			<li><label>交易状态：</label>
				<form:select id="respType" path="respType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('resp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>结算方式：</label>
				<form:select id="settleType" path="settleType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('settle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnSumData" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>商户编号</th> -->
				<th>商户名称</th>
				<!-- <th>手机号</th> -->
				<th>所属一级机构</th>
				<th>所属机构</th>
				<th>交易金额</th>
				
				<th>商户费率</th>
				<th>商户提现费</th>
				<!-- <th>结算金额</th> -->
				
				<!-- <th>结算方式</th> -->
				<!-- <th>交易订单编号</th> -->
				<th>交易类型 </th>
				<!-- <th>平台流水号</th> -->
				<!-- <th>请求时间</th> -->
				<th>交易日期</th>
				<!-- <th>交易时间</th> -->
				<!-- <th>交易状态</th> -->
				<!--  
				<th>应答日期时间</th>
				<th>应答类型</th>
				<th>应答码</th>
				<th>应答描述</th>
				-->
				<!-- <th>借贷记类型 </th>
				<th>支付时间</th>
				<th>对账日期</th>
				<th>接口类型</th> -->
				<!--  
				<th>创建时间</th>
				-->
				<shiro:hasPermission name="trade:tradeDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeDetail">
			<tr>
				<%-- <td>
					${tradeDetail.memberCode}
				</td> --%>
				<td>
				${tradeDetail.memberName}
				</td>
				<%-- 	<td>
					${tradeDetail.mobilePhone}
				</td> --%>
				<td>
					${tradeDetail.agentNameLevel1 }
				</td>
				<td>
					${tradeDetail.officeName}
				</td>
				<td>
					${tradeDetail.money}
				</td>
				<td>
					${tradeDetail.memberTradeRate}
				</td>
				 <td>
					${tradeDetail.memberDrawFee}
				</td>
				<%--<td>
					${tradeDetail.memberSettleMoney}
				</td> --%>
				
				<%-- <td>
					${fns:getDictLabel(tradeDetail.settleType,'settle_type',tradeDetail.settleType)}
				</td> --%>
				<%-- <td>
					${tradeDetail.orderCode}
				</td> --%>
				<td>
					${fns:getDictLabel(tradeDetail.txnType,'txn_type',tradeDetail.txnType)}
				</td>
				<%-- <td>
					${tradeDetail.ptSerialNo}
				</td> --%>
				<%-- <td>
					${tradeDetail.reqDate}
				</td> --%>
				<td>
					${tradeDetail.txDate}
				</td>
				<%-- <td>
					${tradeDetail.txTime}
				</td>
				<td>
					${fns:getDictLabel(tradeDetail.respType,'resp_type',tradeDetail.respType)}
				</td> --%>
				<!--  
				<td>
					${tradeDetail.respDate}
				</td>				
				<td>
					${tradeDetail.respCode}
				</td>
				<td>
					${fns:getDictLabel(tradeDetail.respType,'resp_type','应答类型')}
				</td>
				<td>
					${tradeDetail.respMsg}
				</td>
				-->
				<%-- <td>
					${fns:getDictLabel(tradeDetail.cardType,'card_type','贷记')}
				</td>
				<td>
					${tradeDetail.payTime}
				</td>
				<td>
					${tradeDetail.balanceDate}
				</td>
				<td>
					${fns:getDictLabel(tradeDetail.interfaceType,'interface_type','普通')}
				</td> --%>
				<!--  
				<td>
					<fmt:formatDate value="${tradeDetail.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="trade:tradeDetail:edit"><td>
    				<a href="${ctx}/trade/tradeDetail/form?id=${tradeDetail.id}">修改</a>
					<a href="${ctx}/trade/tradeDetail/delete?id=${tradeDetail.id}" onclick="return confirmx('确认要删除该交易明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="sumDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">
            </button>
            <h4 class="modal-title" id="myModalLabel">统计数据</h4>
         </div>
         <div class="modal-body">
         <div style='text-align: center;'>
         
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">交易总笔数</th>
				<th style="text-align: center;">交易总金额</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="sumCount" style="text-align: center;"></td>
				<td id="sumMoney" style="text-align: center;"></td>
			</tr>
		</tbody>
	</table>

         </div>
         </div>
         <div class="modal-footer">
         <button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
         </div>  
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
function getSumData(){
	var officeId = $("#officeid").val();
	//var memberCode = $("#memberCode").val();
	var memberName = $("#memberName").val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var txnType = $("#txnType").val();
	
	$.ajax({
        type: "post",
        async: false,
        url: "${ctx}/trade/tradeDetail/quick/getSumData",
        data: "officeId="+officeId+"&memberName="+memberName+"&beginTime="+beginTime+"&endTime="+endTime+"&txnType="+txnType,
        success: function(data) {
			$("#sumCount").text(data.sumCount);
			$("#sumMoney").text(data.sumMoney);
			
			$('#sumDataModal').modal('toggle').modal('show');
        },error:function(){
        	top.$.jBox.tip("网络异常");
        }
    });
	
}
</script>
</body>

</html>
