<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易对账管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出交易对账数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/bill/msPayBill/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/bill/msPayBill/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bill/msPayBill/">交易对账列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="msPayBill" action="${ctx}/bill/msPayBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeid" type="hidden" value="${msPayBill.office.id }"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${msPayBill.office.id}" labelName="office.name" labelValue="${msPayBill.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<%-- <li><label>通道商户号：</label>
				<form:input path="merchantCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>交易流水号：</label>
				<form:input id="reqMsgId" path="reqMsgId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<%-- <li><label>交易流水号：</label>
				<form:input path="reqMsgId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>商户名称：</label>
				<form:input id = "memberName" path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input id = "mobilePhone" path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>对账日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${msPayBill.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${msPayBill.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>结算方式：</label>
				<form:select id="settleType" path="settleType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('settle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>商户名称</th>
				<th>手机号</th>
				<th>所属一级机构</th>
				<th>所属机构</th>
				<th>通道商户号</th>
				<th>渠道流水号</th>
				<th>交易流水号</th>
				<th>商户费率</th>
				<th>提现手续费</th>	
				<th>交易金额</th>
				<th>结算方式</th>
				<th>对账日期</th>
				<th>交易状态</th>
				<th>交易类型</th>
				<!-- <th>手续费</th> -->
				<th>支付通道</th>
				<th>借贷记类型</th>
				<th>提现批次流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msPayBill">
			<tr>
				<td>
					${msPayBill.member.name}
				</td>
				<td>
					${msPayBill.mobilePhone }
				</td>
				<td>
					${msPayBill.agentNameLevel1 }
				</td>
				<td>
					${msPayBill.office.name}
				</td>
				<td>
					${msPayBill.merchantCode}
				</td>
				<td>
					${msPayBill.smzfMsgId}
				</td>
				<td>
					${msPayBill.reqMsgId}
				</td>
				<td>
					${msPayBill.memberTradeRate}
				</td>
				<c:choose>
				<c:when test="${msPayBill.settleType eq '1' }">
					<td>
						${msPayBill.member.t1DrawFee}
					</td>
				</c:when>
				<c:when test="${msPayBill.settleType eq '0' }">
					<td>
						${msPayBill.member.t0DrawFee}
					</td>
				</c:when>
				</c:choose>
				<td>
					${msPayBill.amount}
				</td>
				<td>
					${fns:getDictLabel(msPayBill.settleType,'settle_type','无')}
				</td>
				<td>
					${msPayBill.settleDate}
				</td>
				<td>
					${fns:getDictLabel(msPayBill.respType,'resp_type','无')}
				</td>
				<td>
					${msPayBill.transactionType}
				</td>
				<%-- <td>
					${msPayBill.fee}
				</td> --%>
				<!-- 支付通道 -->
				<td>
					${fns:getDictLabel(msPayBill.payWay,'pay_way','无')}
				</td>
				<!-- 借贷卡类型 -->
				<td>
					${fns:getDictLabel(msPayBill.payType,'pay_type','无')}
				</td>
				<td>
					${msPayBill.drawBatchNo}
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
	var memberName = $("#memberName").val();
	var mobilePhone = $("#mobilePhone").val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var smzfMsgId = $("#smzfMsgId").val();
	var settleType = $("#settleType option:selected").val();
	
	$.ajax({
        type: "post",
        async: false,
        url: "${ctx}/bill/msPayBill/getSumData",
        data: "officeId="+officeId+"&memberName="+memberName+"&mobilePhone="+mobilePhone+"&beginTime="+beginTime+"&endTime="+endTime+"&smzfMsgId="+smzfMsgId+"&settleType="+settleType,
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