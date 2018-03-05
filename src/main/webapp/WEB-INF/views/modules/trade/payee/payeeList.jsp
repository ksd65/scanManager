<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>收款人</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	$(".save").click(function(){
	    var id = $("#memberId").val();
	    if(id == ""||id==null||id=="null"){
	    	if($("#updateFlag").val()=="0"){
	    		alert("请选择商户");
		    	return;
	    	}
	    }
	    $("#m-form").submit();
	})
	
});
	function page(n, s) {
        $("#searchForm").attr("action","${ctx}/trade/payee/");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function grantMem(pid){
		$("#payeeId").val(pid);
		$("#updateFlag").val("0");
		$("#memberId").find("option:selected").attr("selected",false);
		$.ajax({
			url:"${ctx }/trade/payee/getMemberPayeeList",
			data:{payeeId:pid},
			type:'post',
			cache:false, 
			async:false,
			dataType:'json',
			success:function(data) {
				
				var list = data.memberList;
				for(var i=0;i<list.length;i++){
					$("#memberId").find("option[value='"+list[i].memberId+"']").attr("selected",true);
				}
				$("#memberId").change();
				if(list.length>0){
					$("#updateFlag").val("1");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
		        alert("请求出错");
		    }
		});
		
		
		
		$('#dataModal').modal('toggle').modal('show');
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/trade/payee/">收款人列表</a></li>
		<li><a href="${ctx}/trade/payee/form">收款人添加</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="payee"
		action="${ctx}/trade/payee/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			 <li><label>收款人：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>收款账号：</label>
				<form:input path="payAccount" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>收款类型：</label>
				<form:select id="payType" path="payType" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('qr_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payee.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payee.endTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="return page();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收款账号</th>
				<th>收款人姓名</th>
				<th>收款类型</th>
				<th>创建时间</th>
				
				<shiro:hasPermission name="trade:payee:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="payee">
				<tr>
					<td>${payee.payAccount}</td>
					<td>${payee.userName }</td>
					<td>${fns:getDictLabel(payee.payType,'qr_pay_type',payee.payType)}</td>
					<td><fmt:formatDate value="${payee.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<shiro:hasPermission name="trade:payee:view"><td>
					<a href="${ctx}/trade/payee/delete?id=${payee.id}" onclick="return confirmx('确认要删除该收款人吗？', this.href)">删除</a>
					<a href="javascript:grantMem('${payee.id}')" >授权专用商户</a>
    			</td></shiro:hasPermission>
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
					<h4 class="modal-title" id="myModalLabel">授权专用商户</h4>
				</div>
				<div class="modal-body">
					<div style='text-align: center;'>
						<form action="${ctx}/trade/payee/grant" id="m-form" method="post">
							<input type="hidden" id="payeeId" name="payeeId" value="">
							<input type="hidden" id="updateFlag" name="updateFlag" value="0">
							<table class="table table-striped table-bordered table-condensed">
								<tbody>
								<tr>
									<td style="text-align: center;">
										商户
									</td>
									<td style="text-align: center;">
										<select id="memberId" name="memberId"  style="width: 200px;" multiple="multiple">
											<c:forEach items="${memberList}" var="status">
												<option value="${status.id}">${status.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								</tbody>
							</table>

						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="save btn btn-primary">保存</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
</body>
</html>