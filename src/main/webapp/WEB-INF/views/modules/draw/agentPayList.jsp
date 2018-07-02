<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代付明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出代付数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/draw/routewayDraw/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/draw/routewayDraw/agentPayList");
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
            var orderNumOuter = $("#orderNumOuter").val();
            var ptSerialNo = $("#ptSerialNo").val();
            var respType = $("#respType").val();

            $.ajax({
                type: "post",
                async: false,
                url: "${ctx}/draw/routewayDraw/getSumData",
                data: "officeId="+officeId+"&beginTime="+beginTime+"&endTime="+endTime+"&memberName="+memberName+
				"&ptSerialNo="+ptSerialNo+"&respType="+respType+"&orderNumOuter="+orderNumOuter,
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
							<th style="text-align: center;">代付总比数</th>
							<th style="text-align: center;">代付结果</th>
							<th style="text-align: center;">代付总金额</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td id="sumCount" style="text-align: center;"></td>
							<td style="text-align: center;">成功</td>
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
		<li class="active"><a href="${ctx}/draw/routewayDraw/agentPayList">代付明细列表</a></li>
		<!--  
		<shiro:hasPermission name="draw:routewayDraw:edit"><li><a href="${ctx}/draw/routewayDraw/form">通道提现明细添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="routewayDraw" action="${ctx}/draw/routewayDraw/agentPayList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${routewayDraw.office.id}" labelName="office.name" labelValue="${routewayDraw.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<!--  
			<li><label>商户编号：</label>
				<form:input path="memberCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			-->
			<li><label>商户名称：</label>
				<form:input id="memberName"  path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
		<!--	<li><label>手机号：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>  -->
			<li><label>商户订单号：</label>
				<form:input id="orderNumOuter" path="orderNumOuter" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>平台流水号：</label>
				<form:input id="ptSerialNo" path="ptSerialNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>审核状态：</label>
				<form:select path="auditStatus" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('draw_audit_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>申请日期：</label>
				<input id="applyBeginTime" name="applyBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.applyBeginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="applyEndTime" name="applyEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.applyEndTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li><label>代付日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>代付结果：</label>
				<form:select id="respType" path="respType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('resp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<input id="btnSumData" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
			<!-- 	<th>手机号</th> -->
				<th>所属机构</th>
				<th>商户编号</th>
				<th>银行名称</th>
				<th>银行卡号</th>
				<th>账户名称</th>
				<th>申请时间</th>
				<th>申请代付金额</th>
				<th>实际代付金额</th>
				<th>代付手续费</th>
				<th>审核状态</th>
				<th>审核备注</th>
				<th>商户订单号</th>
				<th>平台流水号</th>
				<th>代付日期</th>
				<th>代付时间</th>
				<th>代付结果</th>
				<th>代付结果说明</th>
				
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="routewayDraw">
			<tr>
				<td>
					${routewayDraw.member.name}
				</td>
			<!-- 	<td>
					${routewayDraw.mobilePhone }
				</td> -->
				<td>
					${routewayDraw.office.name}
				</td>
				<td>
					${routewayDraw.memberCode}
				</td>
				<td>
					${routewayDraw.bankName}
				</td>  
				<td>
				${fns:replaceCertNoStar(routewayDraw.bankAccount) }
				</td>
				
				<td>
				<c:if test="${routewayDraw.accountName!=null && routewayDraw.accountName!='' }">
				${fns:replaceNameStar(routewayDraw.accountName) }
				</c:if>
				</td>
				<td>
					<fmt:formatDate value="${routewayDraw.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${routewayDraw.drawMoney}
				</td>
				<td>
					${routewayDraw.drawamount}
				</td>
				<td>
					${routewayDraw.drawfee}
				</td>
				<td>
					<c:if test="${routewayDraw.auditStatus=='1'}">待审核</c:if>
					<c:if test="${routewayDraw.auditStatus=='2'}">通过</c:if>
					<c:if test="${routewayDraw.auditStatus=='3'}">不通过</c:if>
				</td>
				<td>
					${routewayDraw.remarks}
				</td>
				<td>
					${routewayDraw.orderNumOuter}
				</td>
				<td>
					${routewayDraw.ptSerialNo}
				</td>
				<%-- <td>
					${routewayDraw.reqDate}
				</td> --%>
				<td>
					${routewayDraw.txDate}
				</td>
				<td>
					${routewayDraw.txTime}
				</td>
				
				<td>
					<c:if test="${routewayDraw.respType=='S'}">成功</c:if>
					<c:if test="${routewayDraw.respType=='E'}">失败</c:if>
					<c:if test="${routewayDraw.respType=='R'}">不确定</c:if>
				</td>
				<td>
					${routewayDraw.respMsg}
				</td>
				
				 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>