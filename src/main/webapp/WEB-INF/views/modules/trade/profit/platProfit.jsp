<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易订单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出平台交易利润数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/trade/profit/platExport");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
		});
		function page(n,s){
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/trade/profit/plat");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li  class="active"><a href="${ctx}/trade/profit/plat">交易利润</a></li>
		<li><a href="${ctx}/trade/profit/platDraw">代付收益</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tradeProfit" action="${ctx}/trade/profit/plat" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${tradeProfit.office.id }"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${tradeProfit.office.id}" labelName="office.name" labelValue="${tradeProfit.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
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
							<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
							<input id="btnSumData" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易日期</th>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>交易方式 </th>
				<th>交易类型 </th>
				<th>交易通道 </th>
				<th>通道商户编码</th>
				<th>交易金额</th>
				<th>平台佣金</th>
				<th>代理商名称 </th>
				<th>代理商佣金 </th>
				<th>上游佣金 </th>
				<th>商户付佣 </th>
				<th>商户应收 </th>
				<th>平台佣金扣率 </th>
				<th>代理商佣金扣率 </th>
				<th>平台成本扣率 </th>
				<th>代理商成本扣率 </th>
				<th>商户成本扣率</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tradeProfit">
			<tr>
				<td>
					${tradeProfit.txnDate}
				</td>
				<td>
					${tradeProfit.memberCode}
				</td>
				<td>
					${tradeProfit.memberName}
				</td>
				<td>
					${fns:getDictLabel(tradeProfit.txnMethod,'txn_method',tradeProfit.txnMethod)}
				</td>
				<td>
					${fns:getDictLabel(tradeProfit.txnType,'txn_type',tradeProfit.txnType)}
				</td>
				<td>
					${fns:getDictLabel(tradeProfit.routeCode,'route_code',tradeProfit.routeCode)}
				</td>
				 
				<td>
					${tradeProfit.merchantCode}
				</td>
				<td>
					${tradeProfit.tradeMoney}
				</td>
				<td>
					${tradeProfit.platProfit}
				</td>
				<td>
					${tradeProfit.agentName}
				</td>
				<td>
					${tradeProfit.agentProfit}
				</td>
				<td>
					${tradeProfit.platCost}
				</td>
				<td>
					${tradeProfit.memberCost}
				</td>
				<td>
					${tradeProfit.settleMoney}
				</td>
				
				<td>
					${tradeProfit.platProfitRate}
				</td>
				<td>
					${tradeProfit.agentProfitRate}
				</td>
				<td>
					${tradeProfit.platTradeRate}
				</td>
				<td>
					${tradeProfit.agentTradeRate}
				</td>
				<td>
					${tradeProfit.memberTradeRate}
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
				<th style="text-align: center;">商户总付佣</th>
				<th style="text-align: center;">商户总应收</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="sumTradeMoney" style="text-align: center;"></td>
				<td id="sumMemberCost" style="text-align: center;"></td>
				<td id="sumSettleMoney" style="text-align: center;"></td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th style="text-align: center;">上游总佣金</th>
				<th style="text-align: center;">平台总佣金</th>
				<th style="text-align: center;">代理商总佣金</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="sumPlatCost" style="text-align: center;"></td>
				<td id="sumPlatProfit" style="text-align: center;"></td>
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
	var officeId = $("#officeid").val();
	//var memberCode = $("#memberCode").val();
	var memberName = $("#memberName").val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var txnMethod = $("#txnMethod").val();
	var txnType = $("#txnType").val();
	
	$.ajax({
        type: "post",
        async: false,
        url: "${ctx}/trade/profit/getPlatSumData",
        data: "officeId="+officeId+"&memberName="+memberName+"&beginTime="+beginTime+"&endTime="+endTime+"&txnMethod="+txnMethod+"&txnType="+txnType,
        success: function(data) {
			$("#sumTradeMoney").text(data.tradeMoney);
			$("#sumMemberCost").text(data.memberCost);
			$("#sumSettleMoney").text(data.settleMoney);
			$("#sumPlatCost").text(data.platCost);
			$("#sumPlatProfit").text(data.platProfit);
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
