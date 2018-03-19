<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出提现数据吗？","系统提示",function(v,h,f){
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
			$("#searchForm").attr("action","${ctx}/draw/routewayDraw/auditList");
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
	function audit(id){
		$("#drawId").val(id);
		$("input[name='auditResult']:first").attr('checked', 'checked');
		$("#auditRemark").val("");
		$('#dataModal').modal('toggle').modal('show');
	}
    function doAudit(){
    	var drawId = $("#drawId").val();
    	if(drawId==""){
    		alert("请选择审核记录");
    		return;
    	}
    	var auditRemark = $.trim($("#auditRemark").val());
		var auditResult = $("input[name='auditResult']:checked").val();
    	if(auditResult=='3'){
    		if(auditRemark==""){
    			alert("请输入审核备注");
    			return;
    		}
    	}
    	$.ajax({
            type: "post",
            async: false,
            url: "${ctx}/draw/routewayDraw/audit",
            data: {id:drawId,auditResult:auditResult,auditRemark:auditRemark},
            success: function(data) {
                if(data.result=='0'){
                	alert("提现提交成功，可稍后查看提现结果")
                }else if(data.result=='-1'){
                	alert(data.msg);
                }
                window.location.href="${ctx}/draw/routewayDraw/auditList";

            },error:function(){
                top.$.jBox.tip("网络异常");
            }
        });
    }    
	</script>
</head>
<body>



	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/draw/routewayDraw/auditList">提现审核列表</a></li>
		<!--  
		<shiro:hasPermission name="draw:routewayDraw:edit"><li><a href="${ctx}/draw/routewayDraw/form">通道提现明细添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="routewayDraw" action="${ctx}/draw/routewayDraw/auditList" method="post" class="breadcrumb form-search">
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
				<form:input path="memberName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
		<!-- 	<li><label>手机号：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> -->
			<li><label>平台流水号：</label>
				<form:input path="ptSerialNo" htmlEscape="false" maxlength="32" class="input-medium"/>
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
			<li><label>提现日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${routewayDraw.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>提现结果：</label>
				<form:select path="respType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('resp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
			<!-- 	<th>手机号</th> -->
				<!--
				<th>所属一级机构</th>
				-->
				<th>所属机构</th>
				<th>商户编号</th>
				<th>提现通道</th>
				<th>银行名称</th>
				<th>银行卡号</th>
				<th>账户名称</th>
				<!--  
				<th>通道商户编号</th>
				-->
				<th>申请时间</th>
				<th>申请提现金额</th>
				<th>实际提现金额</th>
				<th>提现费率</th>
				<th>提现手续费</th>
				<th>审核状态</th>
				<th>审核备注</th>
				<th>平台流水号</th>
				<!-- <th>请求日期时间</th> -->
				
				<th>提现日期</th>
				<th>提现时间</th>
				
				<th>提现结果</th>
				<th>提现结果说明</th>
				<!--<th>交易金额</th>
				
				<th>应答描述</th>
				
				<th>实际提现金额</th>
				<th>提现手续费</th>-->
			<!-- 	<th>商户费率</th>
				<th>交易手续费</th>
				<th>对账日期</th> -->
				<!-- <th>创建时间</th> -->
				  
				<th>操作</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="routewayDraw">
			<tr>
				<td>
					${routewayDraw.member.name}
				</td>
				<!--<td>
					${routewayDraw.mobilePhone }
				</td>
				 
				<td>
					${routewayDraw.agentNameLevel1 }
				</td>
				-->
				<td>
					${routewayDraw.office.name}
				</td>
				<td>
					${routewayDraw.memberCode}
				</td>
				<td>
					${fns:getDictLabel(routewayDraw.routeCode,'draw_route',routewayDraw.routeCode)}
				</td>
				
				<td>
					${routewayDraw.bankName}
				</td>
				<td>
				${routewayDraw.bankAccount}
				</td>
				
				<td>
				${routewayDraw.accountName }
				</td>
				<!--  
				<td>
					${routewayDraw.merchantCode}
				</td>
				-->
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
					${routewayDraw.drawRate}
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
				<!--<td>
					${routewayDraw.money }
				</td>
				  
				<td>
					${routewayDraw.respMsg}
				</td>
				
				<td>
					${routewayDraw.drawamount}
				</td>
				<td>
					${routewayDraw.drawfee}
				</td>
			<!-- 	<td>
					${routewayDraw.memberRate}
				</td>
				<td>
					${routewayDraw.tradefee}
				</td>
				<td>
					${routewayDraw.settleDate}
				</td> -->
				<%-- <td>
					<fmt:formatDate value="${routewayDraw.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				 
				<td>
				<c:if test="${routewayDraw.auditStatus=='1'}">
    				<a href="javascript:audit('${routewayDraw.id}')">审核</a>
    			</c:if>
				</td>
				 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="dataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
					</button>
					<h4 class="modal-title" id="myModalLabel">审核</h4>
				</div>
				<div class="modal-body">
					<div style='text-align: center;'>
						<form action="${ctx}/mem/member/update" id="m-form" method="post">
							<input type="hidden" id="drawId" name="drawId" value="">
							<table class="table table-striped table-bordered table-condensed">
								<tr>
									<td style="text-align: center;">审核结果</td>
									<td style="text-align: left;">
									<input type="radio" value="2" name="auditResult" checked="checked"> 通过
									<input type="radio" value="3" name="auditResult" > 不通过
									</td>
								</tr>
								<tr>
									<td style="text-align: center;">
										备注
									</td>
									<td style="text-align: left;">
										<textarea rows="5" cols="10" id="auditRemark"></textarea>
									</td>
								</tr>
							</table>

							
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="save btn btn-primary" onclick="doAudit()">提交</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
</body>
</html>