<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现对账管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出交易对账数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/bill/msWithdrawBill/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/bill/msWithdrawBill/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
        function getSumData(){
            var officeId = $("#officeIdId").val();
            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();
            var memberName = $("#memberName").val();
            var mobilePhone = $("#mobilePhone").val();

            $.ajax({
                type: "post",
                async: false,
                url: "${ctx}/bill/msWithdrawBill/getSumData",
                data: "officeId="+officeId+"&beginTime="+beginTime+"&endTime="+endTime+"&memberName="+memberName+"&mobilePhone="+mobilePhone,
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
</head>
<body>


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
							<th style="text-align: center;">交易总比数</th>
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

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bill/msWithdrawBill/">提现对账列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="msWithdrawBill" action="${ctx}/bill/msWithdrawBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>通道商户号：</label>
				<form:input path="merchantCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>平台流水号：</label>
				<form:input path="smzfMsgId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${msWithdrawBill.office.id}" labelName="office.name" labelValue="${msWithdrawBill.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="memberName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>对账日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${msWithdrawBill.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${msWithdrawBill.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/></li>
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
				<!-- <th>平台流水号</th> -->
				<th>交易流水号</th>
				<th>交易账号</th>
				<th>交易户名</th>
				<th>交易金额</th>
				<th>商户费率</th>
				<th>提现金额</th>
				<th>提现手续费</th>
				<th>交易手续费</th>
				<th>对账日期</th>
				<th>交易状态</th>
				<!-- <th>创建时间</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msWithdrawBill">
			<tr>
				<td>
					${msWithdrawBill.member.name}
				</td>
				<td>
					${msWithdrawBill.mobilePhone }
				</td>
				<td>
					${msWithdrawBill.agentNameLevel1 }
				</td>
				<td>
					${msWithdrawBill.office.name}
				</td>
				<td>
					${msWithdrawBill.merchantCode}
				</td>
				<%-- <td>
					${msWithdrawBill.smzfMsgId}
				</td> --%>
				<td>
					${msWithdrawBill.reqMsgId}
				</td>
				<td>
					${msWithdrawBill.accNo}
				</td>
				<td>
					${msWithdrawBill.accName}
				</td>
				<td>
					${msWithdrawBill.amount}
				</td>
				<td>
					${msWithdrawBill.t0TradeRate }
				</td>
				<td>
					${msWithdrawBill.drawAmount}
				</td>
				<td>
					${msWithdrawBill.drawFee}
				</td>
				<td>
					${msWithdrawBill.tradeFee}
				</td>
				<td>
					${msWithdrawBill.settleDate}
				</td>
				<td>
					${fns:getDictLabel(msWithdrawBill.respType,'resp_type','无')}
				</td>
				<%-- <td>
					<fmt:formatDate value="${msWithdrawBill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>