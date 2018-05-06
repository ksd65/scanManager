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
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/profit/agent");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/profit/agent">按代理商统计</a></li>
		<li><a href="${ctx}/trade/profit/agentMember">按商户统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeProfit" action="${ctx}/trade/profit/agent" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${tradeProfit.office.id }"/>
		<ul class="ul-form">
			<li><label>代理商名称：</label>
				<form:input id="agentName" path="agentName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li><label>交易时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeProfit.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,maxDate:'#F{$dp.$D(\'endTime\');}'});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${tradeProfit.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
							<input id="btnSumData" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易日期</th>
				<th>代理商名称 </th>
				<th>交易通道 </th>
				<th>交易金额</th>
				<th>分润成本</th>
				<th>分润比例 </th>
				<th>应得分润 </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeProfit">
			<tr>
				<td>
					${tradeProfit.txnDate}
				</td>
				<td>
					${tradeProfit.agentName}
				</td>
				<td>
					${fns:getDictLabel(tradeProfit.routeCode,'route_code',tradeProfit.routeCode)}
				</td>
				 
				<td>
					${tradeProfit.tradeMoney}
				</td>
				<td>
					${tradeProfit.agentCost}
				</td>
				<td>
					${tradeProfit.drawPer}%
				</td>
				<td>
					${tradeProfit.agentProfit}
				</td>
				
				
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
				<th style="text-align: center;">交易总金额</th>
				<th style="text-align: center;">分润总成本</th>
				<th style="text-align: center;">实际总分润</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="sumTradeMoney" style="text-align: center;"></td>
				<td id="sumAgentCost" style="text-align: center;"></td>
				<td id="sumAgentProfit" style="text-align: center;"></td>
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
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var agentName = $("#agentName").val();
	
	$.ajax({
        type: "post",
        async: false,
        url: "${ctx}/trade/profit/getAgentSumData",
        data: "beginTime="+beginTime+"&endTime="+endTime+"&agentName="+agentName,
        success: function(data) {
			$("#sumTradeMoney").text(data.tradeMoney);
			$("#sumAgentCost").text(data.agentCost);
			$("#sumAgentProfit").text(data.agentProfit);
			$('#sumDataModal').modal('toggle').modal('show');
        },error:function(){
        	top.$.jBox.tip("网络异常");
        }
    });
	
}
</script>
</body>

</html>
