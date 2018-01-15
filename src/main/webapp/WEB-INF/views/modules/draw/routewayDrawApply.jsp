<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function apply(){
			var amount = $("#amount").val();
			if(amount==""){
				alert("请输入提现金额");
				return;
			}
			var bindAcc = $("#bindAcc").val();
			if(bindAcc==""){
				alert("请选择提现银行卡");
				return;
			}
			$.ajax({
				url:"${ctx }/draw/routewayDraw/applySubmit",
				data:{drawMoney:amount,bindAccId:bindAcc},
				type:'post',
				cache:false,
				async:false,
				dataType:'json',
				success:function(data) {
					if(data.returnCode=="0000"){//请求成功
						alert("提现申请提交成功");
						window.location.href="${ctx}/draw/routewayDraw";
					}else{
						alert(data.returnMsg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			        alert("请求出错");
			    }
			});

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/draw/routewayDraw/apply">申请提现</a></li>
	</ul><br/>
	<form:form id="inputForm"  action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">通道:</label>
			<div class="controls">
				<select class="input-xlarge">
					<option value="1008">通道1008</option>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">总余额:</label>
			<div class="controls">
				${balanceAccount.resData.balance }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可提现余额:</label>
			<div class="controls">
				${balanceAccount.resData.canDrawMoneyCount }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提现手续费:</label>
			<div class="controls">
				${balanceAccount.resData.drawFee }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提现金额:</label>
			<div class="controls">
				<input id="amount" name="amount" class="input-medium" type="number" value="" maxlength="9"/>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">提现卡号:</label>
			<div class="controls">
				<select class="input-xlarge" id="bindAcc">
					<option value="">请选择</option>
					<c:forEach items="${accList }" var="acclist">
					<option value="${acclist.id }">${acclist.bankName }-${acclist.name }-${acclist.acc }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		
		<div class="form-actions">
			<shiro:hasPermission name="draw:drawApply:view"><input id="btnSubmit" class="btn btn-primary" type="button" value="申请提现" onclick="apply();"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>