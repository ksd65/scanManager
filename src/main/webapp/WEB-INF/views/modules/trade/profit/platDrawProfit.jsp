<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易订单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出平台代付收益数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/trade/profit/platDrawExport");
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
			$("#searchForm").attr("action","${ctx}/trade/profit/platDraw");
			$("#searchForm").submit();
        	return false;
        }
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/trade/profit/plat">交易利润</a></li>
		<li  class="active"><a href="${ctx}/trade/profit/platDraw">代付收益</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="routewayDrawProfit" action="${ctx}/trade/profit/platDraw" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${routewayDrawProfit.office.id }"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${routewayDrawProfit.office.id}" labelName="office.name" labelValue="${routewayDrawProfit.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li><label>代付时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDrawProfit.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,maxDate:'#F{$dp.$D(\'endTime\');}'});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDrawProfit.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
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
				<th>代付日期</th>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>代理商名称 </th>
				<th>交易通道 </th>
				<th>代付（提现）金额</th>
				<th>代付（提现）收益</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="routewayDrawProfit">
			<tr>
				<td>
					${routewayDrawProfit.txnDate}
				</td>
				<td>
					${routewayDrawProfit.memberCode}
				</td>
				<td>
					${routewayDrawProfit.memberName}
				</td>
				<td>
					${routewayDrawProfit.agentName}
				</td>
				<td>
					${fns:getDictLabel(routewayDrawProfit.routeCode,'route_code',routewayDrawProfit.routeCode)}
				</td>
				 
				<td>
					${routewayDrawProfit.money}
				</td>
				<td>
					${routewayDrawProfit.profit}
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
				<th style="text-align: center;">代付（提现）总金额</th>
				<th style="text-align: center;">代付（提现）总收益</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="sumMoney" style="text-align: center;"></td>
				<td id="sumProfit" style="text-align: center;"></td>
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
	
	$.ajax({
        type: "post",
        async: false,
        url: "${ctx}/trade/profit/getPlatDrawSumData",
        data: "officeId="+officeId+"&memberName="+memberName+"&beginTime="+beginTime+"&endTime="+endTime,
        success: function(data) {
			$("#sumMoney").text(data.money);
			$("#sumProfit").text(data.profit);
			$('#sumDataModal').modal('toggle').modal('show');
        },error:function(){
        	top.$.jBox.tip("网络异常");
        }
    });
	
}
</script>
</body>

</html>
